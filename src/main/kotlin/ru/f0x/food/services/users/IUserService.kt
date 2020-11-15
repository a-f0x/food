package ru.f0x.food.services.users

import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.ProfileResponse

interface IUserService {

    fun isEmailExist(email: String): Boolean

    fun registerUser(createUserDTO: CreateUserDTO): ProfileResponse

    fun getUserProfile(user: CustomUserDetails): ProfileResponse


    fun getUserByTelegramId(telegramId: Int): ProfileResponse
}