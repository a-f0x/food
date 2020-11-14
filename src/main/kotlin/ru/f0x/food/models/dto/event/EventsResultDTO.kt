package ru.f0x.food.models.dto.event

import ru.f0x.food.models.dto.food.FoodProductEventDTO
import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDate
import java.time.LocalDateTime

data class EventsResultDTO(
        val eventsPerDay: Map<LocalDate, EventsPerDay>
)

data class EventsPerDay(
        val progress: ProgressDTO,
        val events: Map<LocalDateTime, Events>
)


data class Events(
        val name: String?,
        val time: LocalDateTime,
        val type: EventTypeEnum,
        val kCal: Float,
        val food: List<FoodProductEventDTO>?
)