package ru.f0x.food.repository.events

import ru.f0x.food.models.entity.events.EventsReportRowEntity
import java.time.LocalDate

interface IEventReportRepository {
    fun getEventsBetweenDate(userId: Int, start: LocalDate, end: LocalDate): List<EventsReportRowEntity>
}