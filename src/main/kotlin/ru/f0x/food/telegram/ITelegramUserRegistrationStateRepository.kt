package ru.f0x.food.telegram

interface ITelegramUserRegistrationStateRepository {

    fun saveState(state: TelegramUserRegistrationState)

    fun getState(userInfo: UserInfo): TelegramUserRegistrationState
}