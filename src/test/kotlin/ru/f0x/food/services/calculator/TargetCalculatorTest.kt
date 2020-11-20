package ru.f0x.food.services.calculator

import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


class TargetCalculatorTest {

    @Test
    fun test_gain_activity_MINIMUM_for_MALE() {

        val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(1605864132), ZoneId.systemDefault())
        println(date)
    }


}