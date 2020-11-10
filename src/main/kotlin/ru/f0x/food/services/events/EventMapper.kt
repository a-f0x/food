package ru.f0x.food.services.events

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventResultDTO
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.models.entity.EventEntity
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.FoodEventEntity
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.services.calculator.calculateEventSum
import ru.f0x.food.services.calculator.calculateTargetForUserProfile
import java.time.LocalDateTime

@Component
class EventMapper {

    fun createEntityForFood(
            userId: Int,
            dto: CreateEventForFoodDTO,
            food: FoodProductDTO,
            foodEventEntity: FoodEventEntity,
            createdTime: LocalDateTime): EventEntity {

        val nutrients = food.getNutrients(dto.weightGram)

        return
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

    fun createResult(profile: UserProfileEntity, start: LocalDateTime, end: LocalDateTime, events: List<EventEntity>): EventResultDTO {
        val targetResult = profile.calculateTargetForUserProfile()
        val eventSum = events.calculateEventSum()

        val currentProtein = eventSum.totalProtein
        val targetProtein = targetResult.nutrients.protein.weightGram
        val progressProteinPercent = calcPercentProgress(currentProtein, targetProtein)

        val currentFat = eventSum.totalFat
        val targetFat = targetResult.nutrients.fat.weightGram
        val progressFat = calcPercentProgress(currentFat, targetFat)

        val currentCarb = eventSum.totalCarb
        val targetCarb = targetResult.nutrients.carb.weightGram
        val progressCarb = calcPercentProgress(currentCarb, targetCarb)

        val currentKCal = eventSum.totalKCal
        val targetKCal = targetResult.nutrients.totalKCal
        val progressKCal = calcPercentProgress(currentKCal, targetKCal)

        return EventResultDTO(
                start,
                end,
                targetProtein,
                currentProtein,
                progressProteinPercent,
                targetFat,
                currentFat,
                progressFat,
                targetCarb,
                currentCarb,
                progressCarb,
                targetKCal,
                currentKCal,
                progressKCal
        )
    }

    private fun calcPercentProgress(current: Float, total: Float): Float = current / (total / 100)

}