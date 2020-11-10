package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.TargetEnum

data class TargetCalculationResult(
        val target: TargetEnum,
        val basicKCal: Float,
        val nutrients: NutrientsResult
)