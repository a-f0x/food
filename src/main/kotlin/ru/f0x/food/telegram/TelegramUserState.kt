package ru.f0x.food.telegram

import ru.f0x.food.models.dto.users.CreateUserProfileDTO
import ru.f0x.food.telegram.cases.CaseType

class TelegramUserState private constructor(val info: UserInfo, val case: CaseType) {

    companion object {
        fun initialState(info: UserInfo): TelegramUserState = TelegramUserState(info, CaseType.ON_USER_NOT_FOUND)
    }

    var email: String? = null
    var password: String? = null
    var profile: CreateUserProfileDTO? = null

    private constructor(state: TelegramUserState, case: CaseType) : this(state.info, case) {
        email = state.email
        password = state.password
        profile = state.profile
    }

    fun goTo(case: CaseType): TelegramUserState {
        return TelegramUserState(this, case)
    }

    override fun toString(): String {
        return "TelegramUserState(info=$info, case=$case)"
    }
}