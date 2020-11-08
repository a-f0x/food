package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.CalculationResult
import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum
import ru.f0x.food.models.entity.TargetEnum

/**
 * https://zen.yandex.ru/media/id/5c0eaaff9c61f000aaf6b9be/bju-dlia-pohudeniia-procentnoe-sootnoshenie-i-raschet-sutochnoi-normy-po-dvum-formulam-5c0fb37b71a92900aa02b87a
 * */
class TargetCalculator(
        private val weight: Float,
        private val height: Float,
        private val age: Int,
        private val activity: ActivityEnum,
        private val sex: SexEnum
) : ITargetCalculator {

    override fun calculate(target: TargetEnum): CalculationResult {
        val basicKCal = calculateBasicMetabolismKCal()

        val nutrientResult = when (sex) {
            SexEnum.FEMALE -> {
                FemaleNutrientsCalculator(basicKCal * getTargetCaloriesRate(target)).calculate()
            }
            SexEnum.MALE -> {
                MaleNutrientsCalculator(basicKCal * getTargetCaloriesRate(target)).calculate()
            }
        }
        return CalculationResult(
                target,
                basicKCal,
                nutrientResult
        )
    }

    /**
     * Формула Харриса-Бенедикта
     *  Минимально необходимое количество килокалорий  при выбранном уровне активности
     * что бы вес не изменялся
     * */
    private fun calculateBasicMetabolismKCal(): Float = calculateMinimumBasicMetabolismKCal() * getActivityRate()

    private fun calculateMinimumBasicMetabolismKCal(): Float = when (sex) {
        SexEnum.MALE -> calculateBMRForMale()
        SexEnum.FEMALE -> calculateBMRForFemale()
    }

    private fun getActivityRate(): Float = when (activity) {
        ActivityEnum.MINIMUM -> 1.2f
        ActivityEnum.WEAK -> 1.375f
        ActivityEnum.MEDIUM -> 1.55f
        ActivityEnum.HIGH -> 1.725f
        ActivityEnum.EXTRA_HIGH -> 1.9f
    }

    private fun calculateBMRForFemale(): Float = 447.593f + (9.247f * weight) + (3.098f * height) - (4.33f * age)

    private fun calculateBMRForMale(): Float = 88.362f + (13.397f * weight) + (4.799f * height) - (5.667f * age)

    private fun getTargetCaloriesRate(target: TargetEnum): Float {
        return when (target) {
            TargetEnum.GAIN_WEIGHT -> 1.2f
            TargetEnum.SAVE_WEIGHT -> 1.0f
            TargetEnum.LOSE_WEIGHT -> 0.8f
        }
    }

}