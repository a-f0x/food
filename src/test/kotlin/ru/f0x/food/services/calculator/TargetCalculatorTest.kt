package ru.f0x.food.services.calculator

import org.junit.Test
import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum
import ru.f0x.food.models.entity.TargetEnum

class TargetCalculatorTest {

    @Test
    fun test_gain_activity_MINIMUM_for_MALE() {
        val calc = TargetCalculator(
                weight = 90f,
                height = 180f,
                activity = ActivityEnum.MEDIUM,
                age = 30,
                sex = SexEnum.MALE
        )


        println(calc.calculate(TargetEnum.LOSE_WEIGHT))
        println()
        println("----------------------------------------------")
        println()
        println(calc.calculate(TargetEnum.SAVE_WEIGHT))
        println()
        println("----------------------------------------------")
        println()
        println(calc.calculate(TargetEnum.GAIN_WEIGHT))
    }


}