package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.ITelegramUserRegistrationStateRepository
import ru.f0x.food.telegram.TelegramUserRegistrationState
import ru.f0x.food.telegram.UserInfo

@Component
class CaseResolver(
        private val cases: Map<CaseType, ITelegramCase>,
        private val repo: ITelegramUserRegistrationStateRepository) {

    fun <T : BotApiMethod<BotApiObject>> getRegistrationProfileCase(userInfo: UserInfo, messageText: String?): T? =
            getCase(
                    getState(userInfo).case
            ).process(
                    userInfo,
                    messageText
            ) as T?


    fun <T : BotApiMethod<BotApiObject>> getCase(userInfo: UserInfo, messageText: String?, profile: Profile): T? {
        return getCase(CaseType.DEFAULT).process(userInfo, messageText, profile)

    }

    private fun getCase(caseType: CaseType): ITelegramCase {
        val case = cases[caseType]
        checkNotNull(case) { "No case for event $caseType" }
        return case
    }

    private fun getState(info: UserInfo): TelegramUserRegistrationState = repo.getState(info)
}