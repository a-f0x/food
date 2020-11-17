package ru.f0x.food.telegram

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class TelegramUserRegistrationStateRepository : ITelegramUserRegistrationStateRepository {
    private val storage = ConcurrentHashMap<UserInfo, TelegramUserRegistrationState>()

    @Synchronized
    override fun saveState(state: TelegramUserRegistrationState) {
        storage[state.info] = state
    }

    @Synchronized
    override fun getState(userInfo: UserInfo): TelegramUserRegistrationState {
        val state = storage[userInfo]
        if (state == null) {
            val notFoundState = TelegramUserRegistrationState.initialState(userInfo)
            storage[userInfo] = notFoundState
            return notFoundState
        }
        return state
    }
}