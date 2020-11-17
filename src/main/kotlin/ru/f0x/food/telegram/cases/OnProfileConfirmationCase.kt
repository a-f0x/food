package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.telegram.ITelegramUserRegistrationStateRepository
import ru.f0x.food.telegram.TelegramUserRegistrationState
import ru.f0x.food.telegram.UserInfo
import java.util.*

@Suppress("UNCHECKED_CAST")
@Component
class OnProfileConfirmationCase(
        private val repository: ITelegramUserRegistrationStateRepository,
        private val userService: IUserService
) : ITelegramCase {
    private companion object {
        private const val OOPS_MESSAGE = "Сорян, что то пошло не так. Пройди регистрацию еще раз."
        private const val HELLO_MESSAGE = "Ну все, тебе пизда"
    }

    override val type: CaseType = CaseType.ON_PROFILE_SAVED

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        val state = repository.getState(userInfo)
        val createUserProfileDTO = state.profile ?: return oops(state) as T


        val result = userService.registerUser(
                CreateUserDTO(
                        password = UUID.randomUUID().toString(),
                        userInfo.userId.toString(),
                        createUserProfileDTO
                )
        )
        return SendMessage(userInfo.cid, HELLO_MESSAGE) as T
    }

    private fun oops(state: TelegramUserRegistrationState): SendMessage {
        repository.saveState(state.goTo(CaseType.ON_PROFILE))
        return SendMessage(state.info.cid, OOPS_MESSAGE)
    }

}