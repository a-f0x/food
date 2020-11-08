package ru.f0x.food.models.dto.food

import ru.f0x.food.validators.food.CorrectCreateFoodProduct

@CorrectCreateFoodProduct
class CreateFoodProductDTO(
        name: String,
        manufacturer: String?,
        carbohydrates: Float,
        proteins: Float,
        fats: Float,
        kiloCal: Float
) : FoodProductDTO(
        null,
        name,
        manufacturer,
        carbohydrates,
        proteins,
        fats,
        kiloCal
)