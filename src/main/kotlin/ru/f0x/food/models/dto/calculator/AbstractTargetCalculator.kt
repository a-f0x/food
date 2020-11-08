package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.*

abstract class AbstractTargetCalculator(
        protected val basicMetabolismKCal: Int,
        protected val proteins: NutrientsRange<Protein>,
        protected val fats: NutrientsRange<Fat>,
        protected val carbohydrates: NutrientsRange<Carbohydrate>,
        protected val target: TargetEnum,
        protected val activity: ActivityEnum
) {
    abstract fun calculate(): CalculationResult
}