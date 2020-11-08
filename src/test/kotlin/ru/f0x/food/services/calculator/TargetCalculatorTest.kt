package ru.f0x.food.services.calculator

import org.junit.Test
import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum

class TargetCalculatorTest {

    @Test
    fun test_basic_metabolism_for_MALE() {
        val calc = TargetCalculator(
                weight = 100f,
                height = 200f,
                activity = ActivityEnum.WEAK,
                age = 40,
                sex = SexEnum.MALE
        )

        val result = calc.calculate()
        println(result)


    }

}