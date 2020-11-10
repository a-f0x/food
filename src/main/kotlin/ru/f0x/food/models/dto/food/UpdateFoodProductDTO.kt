package ru.f0x.food.models.dto.food

import ru.f0x.food.validators.food.CorrectUpdateFoodProduct

@CorrectUpdateFoodProduct
class UpdateFoodProductDTO(
        override val id: Int,
        name: String,
        carb: Float,
        protein: Float,
        fat: Float,
        kCal: Float
) : FoodProductDTO(
        id,
        name,
        carb,
        protein,
        fat,
        kCal
)