package ru.f0x.food.models.dto.event

import ru.f0x.food.validators.events.CreateCorrectFoodEvent
import java.time.LocalDateTime

@CreateCorrectFoodEvent
data class CreateEventForFoodDTO(
        val time: LocalDateTime,
        val name: String,
        val weightGram: Float,
        val foodId: Int
)