package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.telegram.ITelegramUserStateRepository
import ru.f0x.food.telegram.TelegramUserState
import ru.f0x.food.telegram.UserInfo

@Suppress("UNCHECKED_CAST")
@Component
class OnRegistrationFinishedTelegramCase(
        private val repository: ITelegramUserStateRepository,
        private val userService: IUserService
) : ITelegramCase {
    private companion object {
        private const val OOPS_MESSAGE = "Сорян, что то пошло не так. Пройди регистрацию еще раз."
        private const val EMAIL_EXIST = "Долго думал, под твоим имейлом уже кто то зарегистрировался"
    }

    override val type: CaseType = CaseType.ON_REGISTRATION_FINISHED


    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?): T {
        val state = repository.getState(userInfo)
        val email = state.email
        val password = state.password
        val profile = state.profile
        if (email == null)
            return oops(state) as T
        if (password == null)
            return oops(state) as T
        if (profile == null)
            return oops(state) as T
        if (userService.isEmailExist(email)) {
            return SendMessage
        }

    }

    private fun oops(state: TelegramUserState): SendMessage {
        repository.saveState(state.goTo(CaseType.ON_USER_NOT_FOUND))
        return SendMessage(state.info.cid.toString(), OOPS_MESSAGE)
    }

}