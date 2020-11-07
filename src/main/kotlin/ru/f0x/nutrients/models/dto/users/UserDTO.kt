package ru.f0x.nutrients.models.dto.users

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
open class UserDTO(
        open val id: Int?,
        open val password: String?,
        val email: String,
        open val profile: UserProfileDTO?
)