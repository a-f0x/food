package ru.f0x.food.models.dto.food

import ru.f0x.food.validators.nutrients.CorrectUpdateNutrient

@CorrectUpdateNutrient
class UpdateFoodProductDTO(
        override val id: Int,
        name: String,
        manufacturer: String?,
        carbohydrates: Float,
        proteins: Float,
        fats: Float,
        kCal: Float
) : FoodProductDTO(
        id,
        name,
        manufacturer,
        carbohydrates,
        proteins,
        fats,
        kCal
)