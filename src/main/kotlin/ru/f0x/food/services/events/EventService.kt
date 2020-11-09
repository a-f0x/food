package ru.f0x.food.services.events

import org.springframework.stereotype.Service
import ru.f0x.food.models.dto.event.Event
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.repository.EventRepository
import ru.f0x.food.services.IDateTimeService
import java.time.LocalDateTime

@Service
class EventService(
        private val repository: EventRepository,
        private val mapper: EventMapper,
        private val dateTimeService: IDateTimeService
) : IEventService {


    override fun addEatingEvent(userId: Int, food: FoodProductDTO, weightGram: Float): Event {
        val currentTime = dateTimeService.getCurrentTime()
        val userTime = currentTime

        return mapper.mapFromEntity(
                repository.save(
                        mapper.createEntityForEating(
                                userId,
                                userTime,
                                currentTime,
                                food,
                                weightGram
                        )
                )
        )
    }

    override fun addWorkoutEvent(userId: Int, kCal: Float): Event {
        return mapper.mapFromEntity(
                repository.save(
                        mapper.createEntityForWorkout(userId, kCal)
                )
        )
    }

    private fun userTime(): LocalDateTime {

    }


    private fun currentTime(): LocalDateTime = dateTimeService.getCurrentTime()

}