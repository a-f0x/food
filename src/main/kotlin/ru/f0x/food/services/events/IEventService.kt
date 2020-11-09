package ru.f0x.food.services.events

import ru.f0x.food.models.dto.event.Event
import ru.f0x.food.models.dto.food.FoodProductDTO

interface IEventService {

    fun addEatingEvent(userId: Int, food: FoodProductDTO, weightGram: Float): Event

    fun addWorkoutEvent(userId: Int, kCal: Float): Event
}