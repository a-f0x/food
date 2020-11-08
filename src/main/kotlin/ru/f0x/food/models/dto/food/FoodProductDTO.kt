package ru.f0x.food.models.dto.food

open class FoodProductDTO(
        open val id: Int?,
        val name: String,
        val manufacturer: String?,
        val carbohydrates: Float,
        val proteins: Float,
        val fats: Float,
        val kiloCal: Float
)