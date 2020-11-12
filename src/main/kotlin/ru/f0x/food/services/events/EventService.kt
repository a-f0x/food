package ru.f0x.food.services.events

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.getOrNull
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventResultDTO
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.FoodEventEntity
import ru.f0x.food.repository.EventRepository
import ru.f0x.food.repository.FoodEventRepository
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.repository.UserProfileRepository
import ru.f0x.food.services.IDateTimeService
import ru.f0x.food.validators.NOT_FOUND_BY_ID
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class EventService(
        private val eventsRepository: EventRepository,
        private val eventMapper: EventMapper,
        private val dateTimeService: IDateTimeService,
        private val profileRepository: UserProfileRepository,
        private val foodRepository: FoodRepository,
        private val foodFoodEventRepository: FoodEventRepository
) : IEventService {


    @Transactional
    override fun addFoodEvent(userId: Int, dto: CreateEventForFoodDTO): EventResultDTO {

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
                        dto.name,
                        dto.time,
                        currentTime(),
                        EventTypeEnum.FOOD,
                        userId,
                        kCal
                ).apply {
                    foodEvent = foodEventEntity
                }
        )
        return createResult(userId, dto.time)
    }

    @Transactional
    override fun addWorkoutEvent(userId: Int, dto: CreateEventForWorkoutDTO): EventResultDTO {
        eventsRepository.save(
                eventMapper.createEventEntity(
                        dto.name,
                        dto.time,
                        currentTime(),
                        EventTypeEnum.WORKOUT,
                        userId,
                        dto.kCal
                )
        )
        return createResult(userId, dto.time)
    }

    private fun createResult(userId: Int, time: LocalDateTime): EventResultDTO {
        val profile = profile(userId)
        val start = time.toLocalDate().atStartOfDay()
        val end = LocalDateTime.of(time.toLocalDate(), LocalTime.MAX)
        val eventsPerDay = eventsRepository.findAllByUserTimeBetween(start, end)
        return eventMapper.createResult(profile, start, end, eventsPerDay)
    }

    private fun profile(userId: Int) = profileRepository.findByUserId(userId)

    private fun currentTime(): LocalDateTime = dateTimeService.getCurrentTime()


}