package ru.f0x.food.services.events

import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventsResultDTO
import ru.f0x.food.models.dto.event.ProgressDTO
import java.time.LocalDate

interface IEventService {

    @Throws(NotAcceptableDataException::class)
    fun addFoodEvent(userId: Int, dto: CreateEventForFoodDTO): ProgressDTO

    fun addWorkoutEvent(userId: Int, dto: CreateEventForWorkoutDTO): ProgressDTO

    fun getEventsBetweenDate(userId: Int, start: LocalDate, end: LocalDate): EventsResultDTO
}