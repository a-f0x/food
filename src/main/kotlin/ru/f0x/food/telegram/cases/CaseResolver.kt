package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import ru.f0x.food.telegram.*

@Component
class CaseResolver(
        private val cases: Map<CaseType, ITelegramCase>,
        private val repo: ITelegramUserStateRepository) {

    private companion object {
        private val newUserState = listOf(
                CaseType.ON_WAIT_EMAIL_FROM_USER,
                CaseType.ON_WAIT_PASSWORD,
                CaseType.ON_REGISTRATION_START,
                CaseType.ON_PROFILE
        )
    }

    fun <T : BotApiMethod<BotApiObject>> getCase(update: Update): T =
            getCase(
                    getState(update).case
            ).process(
                    update.getUserInfo(),
                    update.getMessageText()
            ) as T


    fun <T : BotApiMethod<BotApiObject>> cancel(userInfo: UserInfo): T {
        val state = getState(userInfo)
        if (state.case in newUserState) {
            repo.saveState(TelegramUserState.initialState(state.info))
            return getCase(CaseType.ON_USER_NOT_FOUND).process(userInfo, null)
        }
        TODO()
    }

    private fun getCase(caseType: CaseType): ITelegramCase {
        val case = cases[caseType]
        checkNotNull(case) { "No case for event $caseType" }
        return case
    }

    private fun getState(update: Update): TelegramUserState = getState(update.getUserInfo())

    private fun getState(info: UserInfo): TelegramUserState = repo.getState(info)
}