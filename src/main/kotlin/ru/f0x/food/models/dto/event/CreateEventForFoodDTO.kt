package ru.f0x.food.models.dto.event

import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.validators.events.CreateCorrectFoodEvent
import java.time.LocalDateTime

@CreateCorrectFoodEvent
data class CreateEventForFoodDTO(
        val time: LocalDateTime,
        val weightGram: Float,
        val food: FoodProductDTO
)