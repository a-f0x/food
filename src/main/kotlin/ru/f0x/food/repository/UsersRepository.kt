package ru.f0x.food.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.f0x.food.models.entity.UserEntity


interface UsersRepository : JpaRepository<UserEntity, Int> {
    fun findByLogin(username: String?): UserEntity?

    fun existsByLogin(login: String): Boolean

}