package ru.f0x.food.models.dto.food

open class FoodProductDTO(
        open val id: Int?,
        val name: String,
        val carb: Float,
        val protein: Float,
        val fat: Float,
        val kCal: Float
)