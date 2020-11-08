package ru.f0x.food.services.users

import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.UserDTO

interface IUserService {

    fun isEmailExist(email: String): Boolean

    fun registerUser(createUserDTO: CreateUserDTO): UserDTO

    fun getUserProfile(user: CustomUserDetails): UserDTO
}