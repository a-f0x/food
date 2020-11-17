package ru.f0x.food.telegram.cases

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.UserInfo

interface ITelegramCase {
    val type: CaseType

    fun <T : BotApiMethod<BotApiObject>> process(
            userInfo: UserInfo,
            messageText: String?,
            profile: Profile? = null): T?

    fun onBack() {}
}