package ru.f0x.food.models.dto.users

data class NutrientsResponse(
        val name: String,
        val weightGram: Float,
        val kiloCalories: Float,
        val kiloCaloriesPerGram: Int
)