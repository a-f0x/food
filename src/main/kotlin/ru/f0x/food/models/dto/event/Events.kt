package ru.f0x.food.models.dto.event

import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDateTime

sealed class Event(
        val type: EventTypeEnum,
        val name: String,
        val weightGram: Float,
        val kCal: Float,
        val protein: Float,
        val fat: Float,
        val carb: Float,
        val time: LocalDateTime
)

class EatingEvent(
        name: String,
        weightGram: Float,
        kCal: Float,
        protein: Float,
        fat: Float,
        carb: Float,
        time: LocalDateTime
) : Event(EventTypeEnum.EATING, name, weightGram, kCal, protein, fat, carb, time)

class WorkoutEvent(
        name: String,
        kCal: Float,
        time: LocalDateTime
) : Event(EventTypeEnum.WORKOUT, name, kCal, 0f, 0f, 0f, 0f, time)