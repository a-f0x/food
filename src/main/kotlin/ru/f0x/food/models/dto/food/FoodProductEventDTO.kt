package ru.f0x.food.models.dto.food

class FoodProductEventDTO(
        override val id: Int,
        name: String,
        protein: Float,
        fat: Float,
        carb: Float,
        kCal: Float,
        val weightGram: Float

) : FoodProductDTO(
        id,
        name,
        protein,
        fat,
        carb,
        kCal
)

