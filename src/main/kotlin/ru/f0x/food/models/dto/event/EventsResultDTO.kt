package ru.f0x.food.models.dto.event

import com.fasterxml.jackson.annotation.JsonInclude
import ru.f0x.food.models.dto.food.FoodProductEventDTO
import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDate
import java.time.LocalTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class EventsResultDTO(
        val eventsPerDay: Map<LocalDate, EventsPerDay>
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class EventsPerDay(
        val progress: ProgressDTO,
        val event: Map<LocalTime, List<Event>>
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Event(
        val type: EventTypeEnum,
        val kCal: Float,
        val food: List<FoodProductEventDTO>?
)