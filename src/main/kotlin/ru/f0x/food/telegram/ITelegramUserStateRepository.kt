package ru.f0x.food.telegram

interface ITelegramUserStateRepository {

    fun saveState(state: TelegramUserState)

    fun getState(userInfo: UserInfo): TelegramUserState
}