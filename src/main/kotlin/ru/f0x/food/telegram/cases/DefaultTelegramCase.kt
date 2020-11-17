package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.UserInfo

@Component
class DefaultTelegramCase : ITelegramCase {
    override val type: CaseType = CaseType.DEFAULT


    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        TODO("Not yet implemented")
    }

}