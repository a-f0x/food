package ru.f0x.food.models.dto.users

import ru.f0x.food.validators.users.CorrectCreateUser

@CorrectCreateUser
class CreateUserDTO(
        password: String,
        login: String,
        override val profile: CreateUserProfileDTO

) : UserDTO(
        id = null,
        password = password,
        login = login,
        profile = profile
)