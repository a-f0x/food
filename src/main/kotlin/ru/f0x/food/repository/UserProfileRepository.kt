package ru.f0x.food.repository

import org.springframework.data.repository.CrudRepository
import ru.f0x.food.models.entity.UserProfileEntity

interface UserProfileRepository : CrudRepository<UserProfileEntity, Int> {
    fun findByUserId(userId: Int): UserProfileEntity
}