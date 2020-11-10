package ru.f0x.food.repository

import org.springframework.data.repository.CrudRepository
import ru.f0x.food.models.entity.EventEntity
import java.time.LocalDateTime

interface EventRepository : CrudRepository<EventEntity, Int> {

    fun findAllByUserTimeBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<EventEntity>
}