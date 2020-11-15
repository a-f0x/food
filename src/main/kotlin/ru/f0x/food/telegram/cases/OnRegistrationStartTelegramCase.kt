package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.telegram.ITelegramUserStateRepository
import ru.f0x.food.telegram.PROFILE_MESSAGE
import ru.f0x.food.telegram.UserInfo

@Component
@Suppress("UNCHECKED_CAST")
class OnRegistrationStartTelegramCase(private val repo: ITelegramUserStateRepository) : ITelegramCase {


    override val type: CaseType = CaseType.ON_REGISTRATION_START

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?): T {
        val password = messageText
        val currentState = repo.getState(userInfo)

        repo.saveState(
                currentState.goTo(CaseType.ON_PROFILE).apply {
                    this.password = password
                }
        )
        return SendMessage(userInfo.cid.toString(), PROFILE_MESSAGE) as T
    }
}