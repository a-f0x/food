package ru.f0x.nutrients.models.dto.nutrients

import ru.f0x.nutrients.validators.nutrients.CorrectCreateNutrient

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