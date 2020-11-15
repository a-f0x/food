package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.Carbohydrate
import ru.f0x.food.models.entity.Fat
import ru.f0x.food.models.entity.Protein

data class NutrientsResult(
        val protein: Nutrient<Protein>,
        val fat: Nutrient<Fat>,
        val carb: Nutrient<Carbohydrate>
) {
    val totalKCal = protein.kCal + fat.kCal + carb.kCal
}