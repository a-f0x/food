package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.MAIN_MENU_EVENTS_BUTTON_TEXT
import ru.f0x.food.telegram.MAIN_MENU_MESSAGE_TEXT
import ru.f0x.food.telegram.UserInfo

@Component
@Suppress("UNCHECKED_CAST")
class AddWorkoutCase : BaseMenuCase() {
    override val type: CaseType = CaseType.ADD_WORKOUT_EVENT

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        return SendMessage.builder()
                .text("Сколько кКал потратил")
                .replyMarkup(buildKeyBoard(listOf(
                        MAIN_MENU_EVENTS_BUTTON_TEXT,
                        MAIN_MENU_MESSAGE_TEXT
                ))).chatId(userInfo.cid)
                .build() as T

    }

}