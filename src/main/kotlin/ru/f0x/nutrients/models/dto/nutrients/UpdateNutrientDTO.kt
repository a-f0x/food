package ru.f0x.nutrients.models.dto.nutrients

import ru.f0x.nutrients.validators.nutrients.CorrectUpdateNutrient

@CorrectUpdateNutrient
class UpdateNutrientDTO(
        override val id: Int,
        name: String,
        manufacturer: String?,
        carbohydrates: Float,
        proteins: Float,
        fats: Float,
        kCal: Float
) : NutrientDTO(
        id,
        name,
        manufacturer,
        carbohydrates,
        proteins,
        fats,
        kCal
)