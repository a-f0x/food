package ru.f0x.nutrients.services.calculator

import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResultDTO

interface ITargetCalculator {

    fun calculate(): TargetCalculationResultDTO

}