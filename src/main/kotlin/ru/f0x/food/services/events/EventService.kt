package ru.f0x.food.services.events

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.getOrNull
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventsResultDTO
import ru.f0x.food.models.dto.event.ProgressDTO
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.events.EventsReportRowEntity
import ru.f0x.food.models.entity.events.FoodEventEntity
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.repository.UserProfileRepository
import ru.f0x.food.repository.events.EventRepository
import ru.f0x.food.repository.events.FoodEventRepository
import ru.f0x.food.repository.events.IEventReportRepository
import ru.f0x.food.services.IDateTimeService
import ru.f0x.food.validators.NOT_FOUND_BY_ID
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class EventService(
        private val eventsRepository: EventRepository,
        private val eventMapper: EventMapper,
        private val dateTimeService: IDateTimeService,
        private val profileRepository: UserProfileRepository,
        private val foodRepository: FoodRepository,
        private val foodFoodEventRepository: FoodEventRepository,
        private val eventReportRepository: IEventReportRepository
) : IEventService {


    @Transactional
    override fun addFoodEvent(userId: Int, dto: CreateEventForFoodDTO): ProgressDTO {

        val foodEntity = foodRepository.findById(dto.foodId).getOrNull() ?: throw NotAcceptableDataException(dto,
                mapOf("food_id" to String.format(NOT_FOUND_BY_ID, dto.foodId))
        )

        val foodEventEntity = foodFoodEventRepository.save(
                FoodEventEntity().apply {
                    this.weightGram = dto.weightGram
                    this.food = foodEntity
                }
        )
        val kCal = (foodEntity.kCal / 100) * dto.weightGram
        eventsRepository.save(
                eventMapper.createEventEntity(
                        dto.time,
                        currentTime(),
                        EventTypeEnum.FOOD,
                        userId,
                        kCal
                ).apply {
                    foodEvent = foodEventEntity
                }
        )
        return createResult(userId, dto.time.toLocalDate())
    }

    @Transactional
    override fun addWorkoutEvent(userId: Int, dto: CreateEventForWorkoutDTO): ProgressDTO {
        eventsRepository.save(
                eventMapper.createEventEntity(
                        dto.time,
                        currentTime(),
                        EventTypeEnum.WORKOUT,
                        userId,
                        dto.kCal
                )
        )
        return createResult(userId, dto.time.toLocalDate())
    }

    override fun getEventsBetweenDate(userId: Int, start: LocalDate, end: LocalDate): EventsResultDTO =
            eventMapper.createEventsResult(
                    profile(userId), getBetweenDates(userId, start, end)

            )

    private fun getBetweenDates(userId: Int, start: LocalDate, end: LocalDate): List<EventsReportRowEntity> =
            eventReportRepository.getEventsBetweenDate(userId, start, end)


    private fun createResult(userId: Int, eventUserDate: LocalDate): ProgressDTO {
        val eventsPerDay = getBetweenDates(userId, eventUserDate, eventUserDate)
        return eventMapper.createProgressResult(eventUserDate, profile(userId), eventsPerDay)
    }

    private fun profile(userId: Int) = profileRepository.findByUserId(userId)

    private fun currentTime(): LocalDateTime = dateTimeService.getCurrentTime()
}