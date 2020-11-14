package ru.f0x.food.models.dto.food

class FoodProductEventDTO(
        override val id: Int,
        name: String,
        carb: Float,
        protein: Float,
        fat: Float,
        kCal: Float,
        val weightGram: Float

) : FoodProductDTO(
        id,
        name,
        carb,
        protein,
        fat,
        kCal
)

