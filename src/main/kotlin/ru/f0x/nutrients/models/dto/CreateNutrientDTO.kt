package ru.f0x.nutrients.models.dto

import ru.f0x.nutrients.validators.CorrectCreateNutrient

@CorrectCreateNutrient
class CreateNutrientDTO(
        name: String,
        manufacturer: String?,
        carbohydrates: Float,
        proteins: Float,
        fats: Float,
        kilocalories: Float
) : NutrientDTO(
        null,
        name,
        manufacturer,
        carbohydrates,
        proteins,
        fats,
        kilocalories
)