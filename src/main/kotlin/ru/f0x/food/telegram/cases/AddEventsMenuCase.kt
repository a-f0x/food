package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.EVENT_FOOD_BUTTON_TEXT
import ru.f0x.food.telegram.EVENT_WORKOUT_BUTTON_TEXT
import ru.f0x.food.telegram.UserInfo
import ru.f0x.food.telegram.buildKeyBoard

@Suppress("UNCHECKED_CAST")
@Component
class AddEventsMenuCase : BaseMenuCase() {

    override val type: CaseType = CaseType.ADD_EVENTS_MENU

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        return SendMessage.builder()
                .text("Выбери событие")
                .replyMarkup(
                        buildKeyBoard(
                                listOf(
                                        EVENT_FOOD_BUTTON_TEXT,
                                        EVENT_WORKOUT_BUTTON_TEXT,
                                )
                        )

                ).chatId(userInfo.cid)
                .build() as T
    }
}