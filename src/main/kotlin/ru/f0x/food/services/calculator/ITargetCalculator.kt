package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.TargetCalculationResult
import ru.f0x.food.models.entity.TargetEnum

interface ITargetCalculator {

    fun calculate(target: TargetEnum): TargetCalculationResult

}