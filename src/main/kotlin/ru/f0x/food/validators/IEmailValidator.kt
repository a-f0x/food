package ru.f0x.food.validators

interface IEmailValidator {

    fun validate(email: String): Boolean
}