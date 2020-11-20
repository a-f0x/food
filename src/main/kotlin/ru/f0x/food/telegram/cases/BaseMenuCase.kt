package ru.f0x.food.telegram.cases

import ru.f0x.food.telegram.mainMenu

abstract class BaseMenuCase : ITelegramCase {


    fun openMainMenu(cid: String) = mainMenu(cid)
}