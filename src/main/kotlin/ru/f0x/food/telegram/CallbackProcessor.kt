package ru.f0x.food.telegram

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.telegram.cases.CaseType
import ru.f0x.food.telegram.cases.ITelegramCase

@Component
@Suppress("UNCHECKED_CAST")
class CallbackProcessor(private val cases: Map<CaseType, ITelegramCase>,
                        private val repo: ITelegramUserStateRepository) {

    fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, callbackData: String): T {
        val cb = MessageCallback.getCallBack(callbackData)
        val state = getState(userInfo)
        return when (cb) {
            is ProfileDecline -> {
                repo.saveState(state.goTo(CaseType.ON_REGISTRATION_START).apply { profile = null })
                getCase(CaseType.ON_REGISTRATION_START).process(userInfo, null)
            }
            is ProfileAccept -> {
                repo.saveState(state.goTo(CaseType.ON_REGISTRATION_FINISHED))
                getCase(CaseType.ON_REGISTRATION_FINISHED).process(userInfo, null)
            }
            else -> TODO("state: $state")
        }
    }

    private fun getState(info: UserInfo): TelegramUserState = repo.getState(info)

    private fun getCase(caseType: CaseType): ITelegramCase {
        val case = cases[caseType]
        checkNotNull(case) { "No case for event $caseType" }
        return case
    }

}