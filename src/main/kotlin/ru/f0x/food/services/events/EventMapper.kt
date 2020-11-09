package ru.f0x.food.services.events

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.event.EatingEvent
import ru.f0x.food.models.dto.event.Event
import ru.f0x.food.models.dto.event.WorkoutEvent
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.models.entity.EventEntity
import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDateTime

@Component
class EventMapper {

    fun createEntityForEating(
            userId: Int,
            time: LocalDateTime,
            createdTime: LocalDateTime,
            food: FoodProductDTO,
            weightGram: Float): EventEntity {

        val nutrients = food.getNutrients(weightGram)

        return EventEntity().apply {
            this.type = EventTypeEnum.EATING
            this.userId = userId
            this.kCal = nutrients.totalKCal
            this.protein = nutrients.protein.weightGram
            this.fat = nutrients.fat.weightGram
            this.carb = nutrients.carb.weightGram
            this.weightGram = weightGram
            this.userTime = time
            this.created = createdTime
        }

    }

    fun createEntityForWorkout(userId: Int, time: LocalDateTime, createdTime: LocalDateTime, kCal: Float): EventEntity {
        return EventEntity().apply {
            this.type = EventTypeEnum.WORKOUT
            this.userId = userId
            this.kCal = kCal
            this.userTime = time
            this.created = createdTime
        }
    }


    fun mapFromEntity(entity: EventEntity): Event {
        return when (entity.type) {
            EventTypeEnum.EATING -> EatingEvent(
                    entity.name,
                    entity.weightGram,
                    entity.kCal,
                    entity.protein,
                    entity.fat,
                    entity.carb,
                    entity.userTime
            )

            EventTypeEnum.WORKOUT -> WorkoutEvent(
                    entity.name,
                    entity.kCal,
                    entity.userTime
            )
        }
    }

}