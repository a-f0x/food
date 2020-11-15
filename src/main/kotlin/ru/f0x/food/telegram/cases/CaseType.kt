package ru.f0x.food.telegram.cases

enum class CaseType {

    /**
     * Пользователя нет в базе по его телеграм ид
     * */
    ON_USER_NOT_FOUND,

    /**
     * ждем ввода имейла от пользователя
     * */
    ON_WAIT_EMAIL_FROM_USER,

    /**
     * если емейл нашелся то ждем от него пароля
     * */
    ON_WAIT_PASSWORD,

    /**
     * елси емейла нет то сохраняем его и придумываем новый пароль
     *
     * */
    ON_REGISTRATION_START,

    /**
     * заполнение профиля
     * */
    ON_PROFILE,

    ON_PROFILE_CONFIRMATION,

    ON_REGISTRATION_FINISHED


}