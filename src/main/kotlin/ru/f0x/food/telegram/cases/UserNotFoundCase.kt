package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.*

@Component
@Suppress("UNCHECKED_CAST")
class UserNotFoundCase(private val repo: ITelegramUserRegistrationStateRepository) : ITelegramCase {

    private companion object {
        private val PROFILE_MESSAGE = "Заполни профиль\n" +
                PROFILE_PATTERN +
                ACTIVITY_TYPES_TEXT +
                TARGET_TYPES +
                "Пример для человека в возрасте 30 лет, ростом 180см, весом 90кг, мужского пола со СЛАБОЙ физической активностью и целью  СБРОСИТЬ ВЕС: \n" +
                "30/180/90/М/2/3\n"

    }

    override val type: CaseType = CaseType.ON_USER_NOT_FOUND

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        val cid = userInfo.cid
        val state = repo.getState(userInfo)
        repo.saveState(state.goTo(CaseType.ON_PROFILE))
        return SendMessage(cid, PROFILE_MESSAGE) as T
    }

}

