package ru.f0x.food.services.events

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.event.Event
import ru.f0x.food.models.dto.event.EventsPerDay
import ru.f0x.food.models.dto.event.EventsResultDTO
import ru.f0x.food.models.dto.event.ProgressDTO
import ru.f0x.food.models.dto.food.FoodProductEventDTO
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.models.entity.events.EventEntity
import ru.f0x.food.models.entity.events.EventsReportRowEntity
import ru.f0x.food.services.calculator.calculateEventSum
import ru.f0x.food.services.calculator.calculateTargetForUserProfile
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class EventMapper {

    fun createEventEntity(eventTime: LocalDateTime,
                          currentTime: LocalDateTime,
                          type: EventTypeEnum,
                          userId: Int,
                          kCal: Float): EventEntity {
        return EventEntity().apply {
            this.type = type
            this.userId = userId
            this.kCal = kCal
            this.userTime = eventTime
            this.created = currentTime
        }
    }

    fun createEventsResult(profile: UserProfileEntity, allEvents: List<EventsReportRowEntity>): EventsResultDTO {
        val eventsPerDay = allEvents.groupBy {
            it.groupedByHours.toLocalDate()
        }.mapValues { mapEntry ->
            val date = mapEntry.key
            val events = mapEntry.value
            EventsPerDay(
                    progress = createProgressResult(date, profile, events),
                    event = createEvents(events)
            )
        }.toSortedMap()

        return EventsResultDTO(eventsPerDay)
    }

    private fun createEvents(list: List<EventsReportRowEntity>): Map<LocalTime, List<Event>> =
            list.groupBy { it.groupedByHours.toLocalTime() }.mapValues {
                it.value.groupBy { it.type }.mapValues { entry ->
                    return@mapValues if (entry.key == EventTypeEnum.WORKOUT) {
                        var kCal = 0f
                        entry.value.forEach { row ->
                            kCal += row.eventKCal
                        }
                        Event(entry.key, kCal, null)
                    } else {
                        var kCal = 0f
                        entry.value.forEach { row ->
                            kCal += row.getFoodKCalFromWeightGram()
                        }

                        val foodList: List<FoodProductEventDTO> = entry.value.map { row ->
                            FoodProductEventDTO(
                                    row.foodId!!,
                                    row.foodName!!,
                                    row.getProteinWeightGram(),
                                    row.getFatWeightGram(),
                                    row.getCarbWeightGram(),
                                    row.getFoodKCalFromWeightGram(),
                                    row.getFoodWeightGram()
                            )
                        }

                        Event(entry.key, kCal, foodList)
                    }
                }.map { it.value }
            }

    fun createProgressResult(onDate: LocalDate, profile: UserProfileEntity, events: List<EventsReportRowEntity>): ProgressDTO {
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