package ru.f0x.food.services.events

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.event.*
import ru.f0x.food.models.entity.EventEntity
import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDateTime

@Component
class EventMapper {

    fun createEntityForFood(
            userId: Int,
            dto: CreateEventForFoodDTO,
            createdTime: LocalDateTime): EventEntity {

        val nutrients = dto.food.getNutrients(dto.weightGram)

        return EventEntity().apply {
            this.type = EventTypeEnum.FOOD
            this.userId = userId
            this.kCal = nutrients.totalKCal
            this.protein = nutrients.protein.weightGram
            this.fat = nutrients.fat.weightGram
            this.carb = nutrients.carb.weightGram
            this.weightGram = dto.weightGram
            this.name = dto.food.name
            this.userTime = dto.time
            this.created = createdTime
        }

    }

    fun createEntityForWorkout(userId: Int, dto: CreateEventForWorkoutDTO, createdTime: LocalDateTime): EventEntity {
        return EventEntity().apply {
            this.type = EventTypeEnum.WORKOUT
            this.userId = userId
            this.kCal = dto.kCal
            this.name = dto.name
            this.userTime = dto.time
            this.created = createdTime
        }
    }


    fun mapFromEntity(entity: EventEntity): Event {
        return when (entity.type) {
            EventTypeEnum.FOOD -> FoodEvent(
                    entity.name,
                    entity.userTime,
                    entity.kCal,
                    entity.protein,
                    entity.fat,
                    entity.carb,
                    entity.weightGram

            )

            EventTypeEnum.WORKOUT -> WorkoutEvent(
                    entity.name,
                    entity.userTime,
                    entity.kCal
            )
        }
    }

}