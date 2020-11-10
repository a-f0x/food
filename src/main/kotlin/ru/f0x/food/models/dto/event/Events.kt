package ru.f0x.food.models.dto.event

import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDateTime

sealed class Event(
        val name: String,
        val type: EventTypeEnum,
        val time: LocalDateTime,
        val kCal: Float,

        )

class FoodEvent(
        name: String,
        time: LocalDateTime,
        kCal: Float,
        val protein: Float,
        val fat: Float,
        val carb: Float,
        val weightGram: Float
) : Event(name, EventTypeEnum.FOOD, time, kCal)

class WorkoutEvent(
        name: String,
        time: LocalDateTime,
        kCal: Float
) : Event(name, EventTypeEnum.WORKOUT, time, kCal)