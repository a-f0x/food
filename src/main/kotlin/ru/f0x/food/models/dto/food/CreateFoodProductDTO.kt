package ru.f0x.food.models.dto.food

import ru.f0x.food.validators.nutrients.CorrectCreateNutrient

@CorrectCreateNutrient
class CreateFoodProductDTO(
        name: String,
        manufacturer: String?,
        carbohydrates: Float,
        proteins: Float,
        fats: Float,
        kCal: Float
) : FoodProductDTO(
        null,
        name,
        manufacturer,
        carbohydrates,
        proteins,
        fats,
        kCal
)