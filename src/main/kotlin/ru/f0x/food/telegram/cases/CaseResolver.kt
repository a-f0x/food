package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.telegram.*

@Component
class CaseResolver(
        private val cases: Map<CaseType, ITelegramCase>,
        private val repo: ITelegramUserRegistrationStateRepository) {

    private val continuedCases = mapOf(
            CaseType.ADD_WORKOUT_EVENT to CaseType.ON_WORKOUT_KCAL_ENTERED
    )

    private val messagesToCaseMap = mapOf(
            MAIN_MENU_MESSAGE_TEXT to CaseType.MAIN_MENU,
            MAIN_MENU_PROFILE_BUTTON_TEXT to CaseType.SHOW_PROFILE,
            MAIN_MENU_EVENTS_BUTTON_TEXT to CaseType.EVENTS_MENU,
            EVENTS_MENU_ADD_EVENT_BUTTON_TEXT to CaseType.ADD_EVENTS_MENU,
            EVENT_WORKOUT_BUTTON_TEXT to CaseType.ADD_WORKOUT_EVENT
    )

    fun <T : BotApiMethod<BotApiObject>> getRegistrationProfileCase(userInfo: UserInfo, messageText: String?): T? =
            getCase(
                    getState(userInfo).case
            ).process(
                    userInfo,
                    messageText
            ) as T?


    fun <T : BotApiMethod<BotApiObject>> getCase(userInfo: UserInfo, messageText: String?, profile: Profile): T? {

        val fromMessage = resolveFromMessage(messageText)
        if (fromMessage != null)
            return getCase(fromMessage).process(userInfo, messageText, profile)

        val next = getNextCaseFromContinued(profile.case)
        if (next != null)
            return getCase(next).process(userInfo, messageText, profile)
        return getCase(CaseType.MAIN_MENU).process(userInfo, messageText, profile)

    }

    private fun resolveFromMessage(messageText: String?): CaseType? {
        return messagesToCaseMap[messageText]
    }

    private fun getNextCaseFromContinued(current: CaseType): CaseType? {
        return continuedCases[current]
    }

    private fun getCase(caseType: CaseType): ITelegramCase {
        val case = cases[caseType]
        checkNotNull(case) { "No case for event $caseType" }
        return case
    }

    private fun getState(info: UserInfo): TelegramUserRegistrationState = repo.getState(info)
}