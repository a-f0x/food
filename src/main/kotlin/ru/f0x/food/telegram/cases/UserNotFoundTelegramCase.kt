package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.ITelegramUserRegistrationStateRepository
import ru.f0x.food.telegram.PROFILE_MESSAGE
import ru.f0x.food.telegram.UserInfo

@Component
@Suppress("UNCHECKED_CAST")
class UserNotFoundTelegramCase(private val repo: ITelegramUserRegistrationStateRepository) : ITelegramCase {

    private companion object {
        private const val MESSAGE = "Введи email"
    }

    override val type: CaseType = CaseType.ON_USER_NOT_FOUND

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        val cid = userInfo.cid.toString()
        val state = repo.getState(userInfo)
        repo.saveState(state.goTo(CaseType.ON_PROFILE))
        return SendMessage(cid, PROFILE_MESSAGE) as T
    }

}

