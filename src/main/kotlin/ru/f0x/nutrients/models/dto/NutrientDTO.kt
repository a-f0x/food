package ru.f0x.nutrients.models.dto

open class NutrientDTO(
        open val id: Int?,
        val name: String,
        val manufacturer: String,
        val carbohydrates: Float,
        val proteins: Float,
        val fats: Float,
        val kilocalories: Float
)