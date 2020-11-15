package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.telegram.ITelegramUserStateRepository
import ru.f0x.food.telegram.UserInfo

@Component
@Suppress("UNCHECKED_CAST")
class UserNotFoundTelegramCase(private val repo: ITelegramUserStateRepository) : ITelegramCase {

    private companion object {
        private const val MESSAGE = "Введи email"
    }

    override val type: CaseType = CaseType.ON_USER_NOT_FOUND

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?): T {
        val cid = userInfo.cid.toString()
        val state = repo.getState(userInfo)
        repo.saveState(state.goTo(CaseType.ON_WAIT_EMAIL_FROM_USER))
        return SendMessage(cid, MESSAGE) as T
    }

}

