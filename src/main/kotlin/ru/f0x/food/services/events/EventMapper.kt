package ru.f0x.food.services.events

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.event.*
import ru.f0x.food.models.entity.EventEntity
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.services.calculator.calculateEventSum
import ru.f0x.food.services.calculator.calculateTargetForUserProfile
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class EventMapper {

    fun createEventEntity(eventName: String,
                          eventTime: LocalDateTime,
                          currentTime: LocalDateTime,
                          type: EventTypeEnum,
                          userId: Int,
                          kCal: Float): EventEntity {
        return EventEntity().apply {
            this.type = type
            this.userId = userId
            this.kCal = kCal
            this.name = eventName
            this.userTime = eventTime
            this.created = currentTime
        }
    }

    fun createEventsResult(profile: UserProfileEntity, allEvents: List<EventEntity>): EventsResultDTO {
        val eventsPerDay = allEvents.groupBy {
            it.userTime.toLocalDate()
        }.mapValues { mapEntry ->
            val date = mapEntry.key
            val events = mapEntry.value
            EventsPerDay(
                    progress = createResult(date, profile, events),
                    events = events.map { event ->
                        mapToEventFromEntity(event)
                    }
            )
        }.toSortedMap()

        return EventsResultDTO(eventsPerDay)
    }

    private fun mapToEventFromEntity(entity: EventEntity): Event {
        return when (entity.type) {
            EventTypeEnum.WORKOUT -> WorkoutEvent(entity.name, entity.userTime, -1 * entity.kCal)
            EventTypeEnum.FOOD -> FoodEvent(
                    entity.name,
                    entity.userTime,
                    entity.kCal,
                    entity.getProteinWeightGram(),
                    entity.getFatWeightGram(),
                    entity.getCarbWeightGram(),
                    entity.getFoodWeightGram()
            )
        }

    }


    fun createResult(onDate: LocalDate, profile: UserProfileEntity, events: List<EventEntity>): ProgressDTO {
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

        return ProgressDTO(
                onDate,
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