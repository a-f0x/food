package ru.f0x.food.telegram

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup
import org.telegram.telegrambots.meta.api.objects.Update
import ru.f0x.food.exceptions.UserNotFoundException
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.telegram.cases.CaseResolver
import ru.f0x.food.telegram.configurations.TelegramBotProperties

@Component
class ChemicalBroBot(
        botProperties: TelegramBotProperties,
        private val userService: IUserService,
        private val caseResolver: CaseResolver,
        private val callbackProcessor: CallbackProcessor

) : TelegramLongPollingBot() {

    private companion object {
        private val logger = LoggerFactory.getLogger(ChemicalBroBot::class.java)
    }

    val token = botProperties.token
    val name = botProperties.botName

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = name

    override fun onUpdateReceived(update: Update?) {
        logger.warn("$update")
        update ?: return
        if (update.message == null && update.hasCallbackQuery().not())
            return

        val info = update.getUserInfo()
        val profile = try {
            userService.getUserByTelegramId(info.userId)
        } catch (unfe: UserNotFoundException) {
            logger.info("${unfe.message} info: $info")
            onUpdate(update)
            return
        }
    }

    private fun onUpdate(update: Update) {
        if (update.hasCallbackQuery()) {
            processCallBack(update)
            return
        }

        val text = update.getMessageText()
        if (text == "/cancel") {
            onCancel(update)
            return
        }
        val method: BotApiMethod<BotApiObject> = caseResolver.getCase(update)
        sendApiMethodAsync(method)
    }

    private fun processCallBack(update: Update) {
        val info = update.getUserInfo()
        val callbackData = update.callbackQuery.data
        val mId = update.callbackQuery.message.messageId
        val hideMarkup = EditMessageReplyMarkup(info.cid.toString(), mId, null, null)
        sendApiMethod(hideMarkup)
        val method: BotApiMethod<BotApiObject> = callbackProcessor.process(info, callbackData)
        sendApiMethodAsync(method)
    }

    private fun onCancel(update: Update) {
        val method: BotApiMethod<BotApiObject> = caseResolver.cancel(update.getUserInfo())
        sendApiMethodAsync(method)

    }

}