package ru.f0x.food.telegram

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.TargetEnum
import ru.f0x.food.telegram.cases.CaseType


const val MAIN_MENU_MESSAGE_TEXT = "Главное меню"
const val MAIN_MENU_PROFILE_BUTTON_TEXT = "Профиль"
const val MAIN_MENU_EVENTS_BUTTON_TEXT = "События"
const val MAIN_MENU_NOTIFICATIONS_BUTTON_TEXT = "Напоминания"
const val EVENTS_MENU_ADD_EVENT_BUTTON_TEXT = "Добавить событие"
const val EVENTS_MENU_SHOW_EVENTS_FOR_LAST_ONE_WEEK_BUTTON_TEXT = "За 7 дней"
const val EVENTS_MENU_SHOW_EVENTS_FOR_LAST_TWO_WEEKS_BUTTON_TEXT = "За 14 дней"
const val EVENTS_MENU_SHOW_EVENTS_FOR_LAST_FOUR_WEEKS_BUTTON_TEXT = "За 28 дней"
const val EVENT_FOOD_BUTTON_TEXT = "Еда"
const val EVENT_WORKOUT_BUTTON_TEXT = "Тренеровка"

const val PROFILE_PATTERN = "Возраст/Рост/Вес/Пол/Активность/Цель\n"

private const val PROFILE_IS_NULL_ERROR = "For %s case profile should be not null"

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



fun Update.getUserInfo(): UserInfo {
    if (message != null) {
        return UserInfo(
                message.from.userName,
                message.chatId.toString(),
                message.from.id
        )
    }
    if (hasCallbackQuery()) {
        return UserInfo(
                callbackQuery.from.userName,
                callbackQuery.message.chatId.toString(),
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

fun getProfileNotNullMessage(caseType: CaseType): String {
    return String.format(PROFILE_IS_NULL_ERROR, caseType)
}





