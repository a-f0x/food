package ru.f0x.food.services.events

import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventResultDTO

interface IEventService {

    @Throws(NotAcceptableDataException::class)
    fun addFoodEvent(userId: Int, dto: CreateEventForFoodDTO): EventResultDTO

    fun addWorkoutEvent(userId: Int, dto: CreateEventForWorkoutDTO): EventResultDTO
}