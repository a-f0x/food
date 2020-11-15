package ru.f0x.food.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.f0x.food.models.entity.UserEntity
import java.util.*


interface UsersRepository : JpaRepository<UserEntity, Int> {
    fun findByEmail(username: String?): Optional<UserEntity>

    fun existsByEmail(email: String): Boolean

    fun getByTelegramId(telegramId: Int): UserEntity?
}