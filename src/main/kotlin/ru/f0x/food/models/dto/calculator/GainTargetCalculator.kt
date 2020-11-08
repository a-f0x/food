package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.*

class GainTargetCalculator(
        basicMetabolismKCal: Int,
        activity: ActivityEnum,
        proteins: NutrientsRange<Protein>,
        fats: NutrientsRange<Fat>,
        carbohydrates: NutrientsRange<Carbohydrate>
) : AbstractTargetCalculator(basicMetabolismKCal, proteins, fats, carbohydrates, TargetEnum.GAIN_WEIGHT, activity) {

    override fun calculate(): CalculationResult {
        return when (activity) {
            ActivityEnum.MINIMUM -> calcForMinActivity()
            ActivityEnum.WEAK -> calculateForWeakActivity()
            else -> TODO("not implemented")
        }
    }

    private fun calculateForWeakActivity(): CalculationResult {
        return calcForMinActivity()
    }

    private fun calcForMinActivity(): CalculationResult {
        val minTotalKcal = basicMetabolismKCal
        val pKCal = proteins.min.getKCal()
        val fKCal = fats.min.getKCal()
        val cKCal = carbohydrates.min.getKCal()
        val remainder = minTotalKcal - pKCal - fKCal - cKCal

        var additionalCarbohydratesGram = 0f

        if (remainder > 0)
            additionalCarbohydratesGram = carbohydrates.min.weightGram + remainder / Carbohydrate.kCalPerGram

        return CalculationResult(
                minTotalKcal,
                proteins.max.weightGram,
                fats.min.weightGram,
                additionalCarbohydratesGram
        )
    }
}