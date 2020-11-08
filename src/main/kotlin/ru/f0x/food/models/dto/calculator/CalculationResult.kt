package ru.f0x.food.models.dto.calculator

data class CalculationResult(
        val totalKCal: Int,
        val proteins: Float,
        val fats: Float,
        val carbohydrates: Float
)