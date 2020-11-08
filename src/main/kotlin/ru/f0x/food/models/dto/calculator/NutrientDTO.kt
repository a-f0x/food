package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.NutrientType

data class NutrientDTO<T : NutrientType>(
        val type: T,
        val weightGram: Float
) {
    fun getKCal(): Int = (type.kCalPerGram * weightGram).toInt()
}