package ru.f0x.nutrients.validators

interface IEmailValidator {

    fun validate(email: String): Boolean
}