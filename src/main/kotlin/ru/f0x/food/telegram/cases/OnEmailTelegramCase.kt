package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.telegram.ITelegramUserStateRepository
import ru.f0x.food.telegram.UserInfo
import ru.f0x.food.validators.IEmailValidator

@Component
@Suppress("UNCHECKED_CAST")
class OnEmailTelegramCase(
        private val emailValidator: IEmailValidator,
        private val userService: IUserService,
        private val repo: ITelegramUserStateRepository

) : ITelegramCase {
    private companion object {
        private const val INVALID_EMAIL_MESSAGE = "Невалидный email."
        private const val ENTER_PASSWORD_MESSAGE = "Введи пароль"
        private const val ENTER_NEW_PASSWORD_MESSAGE = "Пароль придумай"
    }

    override val type: CaseType = CaseType.ON_WAIT_EMAIL_FROM_USER

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?): T {
        checkNotNull(messageText) { "messageText is null for case$type" }
        val email = messageText
        val cid = userInfo.cid.toString()
        if (emailValidator.validate(email).not())
            return onInvalidEmail(cid) as T

        val state = repo.getState(userInfo)
        if (userService.isEmailExist(email)) {
            repo.saveState(
                    state.goTo(CaseType.ON_WAIT_PASSWORD).apply {
                        this.email = email
                    }
            )
            return onEmailExist(cid) as T
        }
        repo.saveState(
                state.goTo(CaseType.ON_REGISTRATION_START).apply {
                    this.email = email
                }
        )
        return onSaveEmailAndPasswordQuestion(cid) as T
    }

    private fun onInvalidEmail(cid: String): SendMessage = SendMessage(cid, INVALID_EMAIL_MESSAGE)

    private fun onEmailExist(cid: String): SendMessage = SendMessage(cid, ENTER_PASSWORD_MESSAGE)

    private fun onSaveEmailAndPasswordQuestion(cid: String): SendMessage = SendMessage(cid, ENTER_NEW_PASSWORD_MESSAGE)

}