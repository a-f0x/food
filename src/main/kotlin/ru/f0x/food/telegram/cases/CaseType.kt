package ru.f0x.food.telegram.cases

enum class CaseType {

    /**
     * Пользователя нет в базе по его телеграм ид
     * */
    ON_USER_NOT_FOUND,

    ON_PROFILE,

    ON_PROFILE_CONFIRMATION,

    ON_PROFILE_SAVED,

    DEFAULT

}