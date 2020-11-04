package ru.f0x.nutrients.models.dto

import ru.f0x.nutrients.validators.CorrectUpdateNutrient

@CorrectUpdateNutrient
class UpdateNutrientDTO(
        override val id: Int,
        name: String,
        manufacturer: String,
        carbohydrates: Float,
        proteins: Float,
        fats: Float,
        kilocalories: Float
) : NutrientDTO(
        id,
        name,
        manufacturer,
        carbohydrates,
        proteins,
        fats,
        kilocalories
)