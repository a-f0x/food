package ru.f0x.food.validators

import org.apache.commons.validator.routines.EmailValidator
import org.springframework.stereotype.Component


@Component
class SimpleEmailValidator : IEmailValidator {
    private val validator = EmailValidator.getInstance()

    override fun validate(email: String): Boolean = validator.isValid(email)

}