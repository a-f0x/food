package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.CalculationResult

interface ITargetCalculator {

    fun calculate(): CalculationResult

}