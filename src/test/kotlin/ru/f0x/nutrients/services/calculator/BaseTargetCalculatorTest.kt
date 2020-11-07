package ru.f0x.nutrients.services.calculator

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.f0x.nutrients.models.entity.ActivityEnum
import ru.f0x.nutrients.models.entity.SexEnum

class BaseTargetCalculatorTest {

    @Test
    fun test_basic_metabolism_for_MALE() {
        val calc = BaseTargetCalculator(
                weight = 59f,
                height = 170f,
                activity = ActivityEnum.WEAK,
                age = 25,
                sex = SexEnum.MALE
        )

        val basicMetabolism = calc.calculateBasicMetabolism()
        assertEquals(2107, basicMetabolism)
    }


    @Test
    fun test_basic_metabolism_for_FEMALE() {
        val calc = BaseTargetCalculator(
                weight = 59f,
                height = 170f,
                activity = ActivityEnum.WEAK,
                age = 25,
                sex = SexEnum.FEMALE
        )

        val basicMetabolism = calc.calculateBasicMetabolism()
        assertEquals(1878, basicMetabolism)
    }

}