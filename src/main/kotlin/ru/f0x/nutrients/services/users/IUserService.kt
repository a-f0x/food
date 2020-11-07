package ru.f0x.nutrients.services.users

import ru.f0x.nutrients.models.dto.CustomUserDetails
import ru.f0x.nutrients.models.dto.users.CreateUserDTO
import ru.f0x.nutrients.models.dto.users.UserDTO

interface IUserService {

    fun isEmailExist(email: String): Boolean

    fun registerUser(createUserDTO: CreateUserDTO): UserDTO

    fun getUserProfile(user: CustomUserDetails): UserDTO
}