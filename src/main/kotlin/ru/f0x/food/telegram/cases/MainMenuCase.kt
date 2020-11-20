package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.UserInfo

@Suppress("UNCHECKED_CAST")
@Component
class MainMenuCase : BaseMenuCase() {

    override val type: CaseType = CaseType.MAIN_MENU

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {

        return openMainMenu(userInfo.cid) as T
    }

}