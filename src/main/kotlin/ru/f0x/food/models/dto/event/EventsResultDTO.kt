package ru.f0x.food.models.dto.event

import java.time.LocalDate

data class EventsResultDTO(
        val eventsPerDay: Map<LocalDate, EventsPerDay>
)

data class EventsPerDay(
        val progress: ProgressDTO,
        val events: List<Event>
)