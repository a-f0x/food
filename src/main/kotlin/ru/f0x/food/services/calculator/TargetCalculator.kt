package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.Nutrient
import ru.f0x.food.models.dto.calculator.NutrientsResult
import ru.f0x.food.models.dto.calculator.TargetCalculationResult
import ru.f0x.food.models.entity.*

/**
 * https://zen.yandex.ru/media/id/5c0eaaff9c61f000aaf6b9be/bju-dlia-pohudeniia-procentnoe-sootnoshenie-i-raschet-sutochnoi-normy-po-dvum-formulam-5c0fb37b71a92900aa02b87a
 * */
class TargetCalculator(
        private val weight: Float,
        private val height: Float,
        private val age: Int,
        private val sex: SexEnum,
        private val target: TargetEnum,
        private val activity: ActivityEnum

) {

    fun calculate(): TargetCalculationResult {
        val basicKCal = calculateBasicMetabolismKCal()
        val nutrientResult = when (sex) {
            SexEnum.FEMALE -> {
                calculateFemaleNutrientResult(basicKCal * getTargetCaloriesRate(target))
            }
            SexEnum.MALE -> {
                calculateMaleNutrientResult(basicKCal * getTargetCaloriesRate(target))
            }
        }
        return TargetCalculationResult(
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


    /**
     * Исходя из пропорции 2,2:2:4,5 получаем: 2,2 + 2 + 4,5 = 8,7 частей.
     * Суточную калорийность (1 300 ккал) делим на 8,7 частей, получается, что 149,4 ккал приходится на 1 часть.
     * Умножаем полученное количество на данные из пропорции:
     * для белков 149,4 ккал х 2,2 = 328,7 ккал;
     * для жиров 149,4 ккал х 2 = 298,8 ккал;
     * для углеводов 149,4 ккал х 4,5 = 672,3 ккал.
     * Высчитываем БЖУ в граммах:
     * 328,7 ккал / 4 = 82,2 г (белков);
     * 298,8 ккал / 9 = 33,2 г (жиров);
     * 672,3 ккал / 4 = 168,1 г (углеводов).
     * */
    private fun calculateFemaleNutrientResult(basicMetabolismKCal: Float): NutrientsResult {
        val part = basicMetabolismKCal / 8.7f
        val protein = Nutrient.createFromKCal(Protein, part * 2.2f)
        val fat = Nutrient.createFromKCal(Fat, part * 2f)
        val carbohydrate = Nutrient.createFromKCal(Carbohydrate, part * 4.5f)

        return NutrientsResult(protein, fat, carbohydrate)
    }

    /**
     * Исходя из пропорции 3:2:5 получаем: 3 + 2 + 5 = 10 частей.
     * Суточную калорийность (1 600 ккал) делим на 10 частей, получается, что 160 ккал приходится на 1 часть.
     * Умножаем полученное количество на данные из пропорции:
     * для белков 160 ккал х 3 = 480 ккал;
     * для жиров 160 ккал х 2 = 320 ккал;
     * для углеводов 160 ккал х 5 = 800 ккал.
     * Высчитываем БЖУ в граммах:
     *
     * 480 ккал / 4 = 120 г (белков);
     * 320 ккал / 9 = 35,6 г (жиров);
     * 800 ккал / 4 = 200 г (углеводов).
     * */
    private fun calculateMaleNutrientResult(basicMetabolismKCal: Float): NutrientsResult {
        val part = basicMetabolismKCal / 10f
        val protein = Nutrient.createFromKCal(Protein, part * 3)
        val fat = Nutrient.createFromKCal(Fat, part * 2)
        val carbohydrate = Nutrient.createFromKCal(Carbohydrate, part * 5)

        return NutrientsResult(protein, fat, carbohydrate)
    }


}