package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.UserInfo
import ru.f0x.food.telegram.getProfileNotNullMessage

@Component
class ShowProfileTelegramCase : ITelegramCase {
    override val type: CaseType = CaseType.SHOW_PROFILE


    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        checkNotNull(profile) { getProfileNotNullMessage(type) }
        return SendMessage(userInfo.cid, profile.toString()) as T
    }

}