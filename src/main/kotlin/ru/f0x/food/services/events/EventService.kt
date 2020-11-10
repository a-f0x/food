package ru.f0x.food.services.events

import org.springframework.stereotype.Service
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.Event
import ru.f0x.food.repository.EventRepository
import ru.f0x.food.services.IDateTimeService
import java.time.LocalDateTime

@Service
class EventService(
        private val repository: EventRepository,
        private val mapper: EventMapper,
        private val dateTimeService: IDateTimeService
) : IEventService {

    override fun addFoodEvent(userId: Int, dto: CreateEventForFoodDTO): Event {
        return mapper.mapFromEntity(
                repository.save(
                        mapper.createEntityForFood(
                                userId,
                                dto,
                                currentTime()
                        )
                )
        )
    }

    override fun addWorkoutEvent(userId: Int, dto: CreateEventForWorkoutDTO): Event {
        return mapper.mapFromEntity(
                repository.save(
                        mapper.createEntityForWorkout(
                                userId,
                                dto,
                                currentTime()
                        )
                )
        )
    }

    private fun currentTime(): LocalDateTime = dateTimeService.getCurrentTime()

}