package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.Carbohydrate
import ru.f0x.food.models.entity.Fat
import ru.f0x.food.models.entity.Protein

data class NutrientsResult(
        val proteins: Nutrient<Protein>,
        val fats: Nutrient<Fat>,
        val carbohydrates: Nutrient<Carbohydrate>
) {
    val totalKCal = proteins.kCal + fats.kCal + carbohydrates.kCal

    val totalWeightGram = proteins.weightGram + fats.weightGram + carbohydrates.weightGram
}