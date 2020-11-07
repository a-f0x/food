package ru.f0x.nutrients.services.calculator

import ru.f0x.nutrients.models.entity.ActivityEnum
import ru.f0x.nutrients.models.entity.SexEnum

open class BaseTargetCalculator(
        protected val weight: Float,
        protected val height: Float,
        protected val age: Int,
        protected val activity: ActivityEnum,
        private val sex: SexEnum
) {
    private companion object {
        private const val WEIGHT_RATE = 10F
        private const val HEIGHT_RATE = 6.25F
        private const val AGE_RATE = 5F
        private const val FEMALE_CONST = -161F
        private const val MALE_CONST = 5F

    }

    fun calculateBasicMetabolism(): Int = when (sex) {
        SexEnum.MALE -> calculate(MALE_CONST)
        SexEnum.FEMALE -> calculate(FEMALE_CONST)
    }

    private fun calculate(sexConst: Float): Int {
        return (
                ((WEIGHT_RATE * weight) + (HEIGHT_RATE * height) - (AGE_RATE * age) + sexConst) * getActivityRate()
                ).toInt()
    }

    private fun getActivityRate(): Float = when (activity) {
        ActivityEnum.MINIMUM -> 1.2f
        ActivityEnum.WEAK -> 1.375f
        ActivityEnum.MEDIUM -> 1.55f
        ActivityEnum.HIGH -> 1.7f
        ActivityEnum.EXTRA_HIGH -> 1.9f
    }
}