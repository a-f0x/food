package ru.f0x.food.models.dto.event

import ru.f0x.food.models.entity.EventTypeEnum

interface IEvent {

    val canCalc: Boolean

    val type: EventTypeEnum

//    fun calculate(): NutrientsResult
}