package ru.f0x.nutrients.services.calculator

import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResult

interface ITargetCalculator {

    fun calculate(): TargetCalculationResult

}