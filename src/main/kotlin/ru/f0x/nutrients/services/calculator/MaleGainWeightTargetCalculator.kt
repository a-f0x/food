package ru.f0x.nutrients.services.calculator

import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResult
import ru.f0x.nutrients.models.entity.ActivityEnum

class MaleGainWeightTargetCalculator(
        private val weight: Float,
        private val height: Float,
        private val age: Int,
        private val activity: ActivityEnum) : ITargetCalculator {

    override fun calculate(): TargetCalculationResult {
        TODO("Not yet implemented")
    }
}