package ru.f0x.food.services.users

import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.cases.CaseType

interface IUserService {

    fun isLoginExist(login: String): Boolean

    fun registerUser(createUserDTO: CreateUserDTO): Profile

    fun getUserProfile(user: CustomUserDetails): Profile

    fun getUserByLogin(login: String): Profile?

    fun goToCase(profileId: Int, case: CaseType)
}