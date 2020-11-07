package ru.f0x.nutrients.models.dto.users

import ru.f0x.nutrients.validators.users.CorrectCreateUser

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