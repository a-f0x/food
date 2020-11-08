package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.NutrientType


data class NutrientsRange<T : NutrientType>(val min: NutrientDTO<T>, val max: NutrientDTO<T>)