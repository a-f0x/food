package ru.f0x.food.telegram.cases

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

abstract class BaseMenuCase : ITelegramCase {

    fun buildKeyBoard(vararg rows: List<String>): ReplyKeyboardMarkup {
        val rowsButtons = mutableListOf<KeyboardRow>()

        rows.forEach { buttonNames ->
            rowsButtons.add(
                    KeyboardRow().apply {
                        addAll(buttonNames)
                    }
            )
        }
        return ReplyKeyboardMarkup().apply {
            resizeKeyboard = true
            keyboard = rowsButtons

        }
    }

}