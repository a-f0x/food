package ru.f0x.food.repository.events

import org.springframework.data.repository.CrudRepository
import ru.f0x.food.models.entity.events.EventEntity
import java.time.LocalDateTime

interface EventRepository : CrudRepository<EventEntity, Int> {

    fun findByUserIdAndUserTimeBetweenOrderByUserTime(userId: Int, startDate: LocalDateTime, endDate: LocalDateTime): List<EventEntity>
}