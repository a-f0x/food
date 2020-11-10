package ru.f0x.food.models.dto.food

import ru.f0x.food.validators.food.CorrectCreateFoodProduct

@CorrectCreateFoodProduct
class CreateFoodProductDTO(
        name: String,
        carb: Float,
        protein: Float,
        fats: Float,
        kCal: Float
) : FoodProductDTO(
        null,
        name,
        carb,
        protein,
        fats,
        kCal
)