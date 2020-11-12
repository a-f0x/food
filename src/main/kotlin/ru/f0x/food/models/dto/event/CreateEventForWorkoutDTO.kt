package ru.f0x.food.models.dto.event

import ru.f0x.food.validators.events.CreateCorrectWorkoutEvent
import java.time.LocalDateTime

@CreateCorrectWorkoutEvent
data class CreateEventForWorkoutDTO(
        val name: String,
        val time: LocalDateTime,
        val kCal: Float
)