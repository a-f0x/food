package ru.f0x.nutrients.repository

import org.springframework.data.repository.CrudRepository
import ru.f0x.nutrients.models.entity.UserProfileEntity

interface UserProfileRepository : CrudRepository<UserProfileEntity, Int> {
    fun findByUserId(userId: Int): UserProfileEntity
}