package ru.f0x.food.telegram

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.TargetEnum

const val PROFILE_PATTERN = "Возраст/Рост/Вес/Пол/Активность/Цель\n"

val ACTIVITY_TYPES_TEXT = "Виды активности:\n" +
        "1. ${ActivityEnum.MINIMUM.simpleName} - ${ActivityEnum.MINIMUM.description} \n" +
        "2. ${ActivityEnum.WEAK.simpleName} - ${ActivityEnum.WEAK.description}\n" +
        "3. ${ActivityEnum.MEDIUM.simpleName} - ${ActivityEnum.MEDIUM.description}" +
        "4. ${ActivityEnum.HIGH.simpleName} - ${ActivityEnum.HIGH.description}\n" +
        "5. ${ActivityEnum.EXTRA_HIGH.simpleName} - ${ActivityEnum.EXTRA_HIGH.description}\n"

val TARGET_TYPES = "Цели:\n" +
        "1. ${TargetEnum.LOSE_WEIGHT.simpleName}\n" +
        "2. ${TargetEnum.GAIN_WEIGHT.simpleName}\n" +
        "3. ${TargetEnum.SAVE_WEIGHT.simpleName}\n"

val PROFILE_MESSAGE = "Заполни профиль\n" +
        PROFILE_PATTERN +
        ACTIVITY_TYPES_TEXT +
        TARGET_TYPES +
        "Пример для человека в возрасте 30 лет, ростом 180см, весом 90кг, мужского пола со СЛАБОЙ физической активностью и целью  СБРОСИТЬ ВЕС: \n" +
        "30/180/90/М/2/3\n"


fun Update.getUserInfo(): UserInfo {
    if (message != null) {
        return UserInfo(
                message.from.userName,
                message.chatId,
                message.from.id
        )
    }
    if (hasCallbackQuery()) {
        return UserInfo(
                callbackQuery.from.userName,
                callbackQuery.message.chatId,
                callbackQuery.from.id
        )
    }
    throw IllegalStateException("No personal data for $this")
}


fun buildMessageWithButtons(cid: String, messageText: String, buttonsCallback: List<ButtonWithCallback>): SendMessage {
    val markupInline = InlineKeyboardMarkup()
    val rowsInline = ArrayList<List<InlineKeyboardButton>>()
    val rowInline = ArrayList<InlineKeyboardButton>()
    rowInline.addAll(
            buttonsCallback.map {
                InlineKeyboardButton().apply {
                    text = it.buttonText
                    callbackData = it.callback
                }
            }
    )
    rowsInline.add(rowInline)
    markupInline.keyboard = rowsInline
    return SendMessage(cid, messageText).apply {
        enableMarkdown(true)
        replyMarkup = markupInline
    }
}

fun Update.getMessageText(): String = message.text






