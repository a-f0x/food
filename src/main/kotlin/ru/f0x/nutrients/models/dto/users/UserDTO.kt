package ru.f0x.nutrients.models.dto.users

open class UserDTO(
        open val id: Int?,
        open val password: String?,
        open val profile: UserProfileDTO?,
        val email: String
)