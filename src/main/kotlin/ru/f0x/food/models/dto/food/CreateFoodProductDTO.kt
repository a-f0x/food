package ru.f0x.food.models.dto.food

import ru.f0x.food.validators.food.CorrectCreateFoodProduct

@CorrectCreateFoodProduct
class CreateFoodProductDTO(
        name: String,
        protein: Float,
        fats: Float,
        carb: Float,
        kCal: Float
) : FoodProductDTO(
        null,
        name,
        protein,
        fats,
        carb,
        kCal
)