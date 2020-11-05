package ru.f0x.nutrients.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.f0x.nutrients.models.entity.User
import java.util.*


interface UsersRepository : JpaRepository<User, Int> {
    fun findByName(username: String?): Optional<User>
}