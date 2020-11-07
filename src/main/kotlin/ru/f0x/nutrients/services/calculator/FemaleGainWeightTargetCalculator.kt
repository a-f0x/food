package ru.f0x.nutrients.services.calculator

import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResult
import ru.f0x.nutrients.models.entity.ActivityEnum
import ru.f0x.nutrients.models.entity.SexEnum

class FemaleGainWeightTargetCalculator(
        weight: Float,
        height: Float,
        age: Int,
        activity: ActivityEnum) : BaseTargetCalculator(weight, height, age, activity, SexEnum.FEMALE), ITargetCalculator {

    override fun calculate(): TargetCalculationResult {
        TODO("Not yet implemented")
    }
}