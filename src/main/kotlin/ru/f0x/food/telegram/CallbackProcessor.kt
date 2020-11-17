package ru.f0x.food.telegram

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.cases.CaseType
import ru.f0x.food.telegram.cases.ITelegramCase

@Component
@Suppress("UNCHECKED_CAST")
class CallbackProcessor(private val cases: Map<CaseType, ITelegramCase>,
                        private val repo: ITelegramUserRegistrationStateRepository) {

    fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, callbackData: String, profile: Profile?): T? {
        return when (val cb = MessageCallback.getCallBack(callbackData)) {
            is ProfileDecline -> {
                val state = getState(userInfo)
                repo.saveState(state.goTo(CaseType.ON_PROFILE).apply { this.profile = null })
                getCase(CaseType.ON_PROFILE).process(userInfo, null)
            }
            is ProfileAccept -> {
                val state = getState(userInfo)
                repo.saveState(state.goTo(CaseType.ON_PROFILE_SAVED))
                getCase(CaseType.ON_PROFILE_SAVED).process(userInfo, null)
            }
            else -> TODO("cb: $cb")
        }
    }

    private fun getState(info: UserInfo): TelegramUserRegistrationState = repo.getState(info)

    private fun getCase(caseType: CaseType): ITelegramCase {
        val case = cases[caseType]
        checkNotNull(case) { "No case for event $caseType" }
        return case
    }

}