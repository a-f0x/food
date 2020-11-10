package ru.f0x.food.services.events

import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.Event

interface IEventService {

    fun addFoodEvent(userId: Int, dto: CreateEventForFoodDTO): Event

    fun addWorkoutEvent(userId: Int, dto: CreateEventForWorkoutDTO): Event
}