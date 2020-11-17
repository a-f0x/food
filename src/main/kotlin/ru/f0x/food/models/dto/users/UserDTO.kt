package ru.f0x.food.models.dto.users

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
open class UserDTO(
        open val id: Int?,
        open val password: String?,
        login: String,
        open val profile: UserProfileDTO?
) {
    val login: String = login
        get() = field.toLowerCase()
}