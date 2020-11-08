package ru.f0x.food.models.dto.users

import ru.f0x.food.validators.users.CorrectCreateUser

@CorrectCreateUser
class CreateUserDTO(
        password: String,
        email: String,
        override val profile: CreateUserProfileDTO

) : UserDTO(
        id = null,
        password = password,
        email = email,
        profile = profile
)