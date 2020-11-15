package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.telegram.UserInfo

@Component
@Suppress("UNCHECKED_CAST")
class OnPasswordTelegramCase() : ITelegramCase {
    override val type: CaseType = CaseType.ON_WAIT_PASSWORD

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?): T {
        TODO("Not yet implemented")
    }
}