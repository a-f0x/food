package ru.f0x.food.telegram

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class TelegramUserStateRepository : ITelegramUserStateRepository {
    private val storage = ConcurrentHashMap<UserInfo, TelegramUserState>()

    @Synchronized
    override fun saveState(state: TelegramUserState) {
        storage[state.info] = state
    }

    @Synchronized
    override fun getState(userInfo: UserInfo): TelegramUserState {
        val state = storage[userInfo]
        if (state == null) {
            val notFoundState = TelegramUserState.initialState(userInfo)
            storage[userInfo] = notFoundState
            return notFoundState
        }
        return state
    }
}