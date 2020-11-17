package ru.f0x.food.telegram

import ru.f0x.food.models.dto.users.CreateUserProfileDTO
import ru.f0x.food.telegram.cases.CaseType

class TelegramUserRegistrationState private constructor(val info: UserInfo, val case: CaseType) {

    companion object {
        fun initialState(info: UserInfo): TelegramUserRegistrationState = TelegramUserRegistrationState(
                info, CaseType.ON_USER_NOT_FOUND
        )
    }

    var profile: CreateUserProfileDTO? = null

    private constructor(state: TelegramUserRegistrationState, case: CaseType) : this(state.info, case) {
        profile = state.profile
    }

    fun goTo(case: CaseType): TelegramUserRegistrationState {
        return TelegramUserRegistrationState(this, case)
    }

    override fun toString(): String {
        return "TelegramUserState(info=$info, case=$case)"
    }
}