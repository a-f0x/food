package ru.f0x.food.telegram.cases

enum class CaseType {

    /**
     * Пользователя нет в базе по его телеграм ид
     * */
    ON_USER_NOT_FOUND,

    ON_PROFILE,

    ON_PROFILE_CONFIRMATION,

    ON_PROFILE_SAVED,

    MAIN_MENU,

    EVENTS_MENU,

    ADD_EVENTS_MENU,

    SHOW_PROFILE,

    ADD_WORKOUT_EVENT,
    ON_WORKOUT_KCAL_ENTERED


}