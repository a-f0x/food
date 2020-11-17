package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.*

@Component
class EventsMenuCase : BaseMenuCase() {
    override val type: CaseType = CaseType.EVENTS_MENU

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        return SendMessage.builder()
                .text("События")
                .replyMarkup(
                        buildKeyBoard(
                                listOf(
                                        EVENTS_MENU_ADD_EVENT_BUTTON_TEXT,
                                        MAIN_MENU_MESSAGE_TEXT
                                ),
                                listOf(
                                        EVENTS_MENU_SHOW_EVENTS_FOR_LAST_ONE_WEEK_BUTTON_TEXT,
                                        EVENTS_MENU_SHOW_EVENTS_FOR_LAST_TWO_WEEKS_BUTTON_TEXT,
                                        EVENTS_MENU_SHOW_EVENTS_FOR_LAST_FOUR_WEEKS_BUTTON_TEXT
                                )

                        )
                )
                .chatId(userInfo.cid)
                .build() as T
    }

}