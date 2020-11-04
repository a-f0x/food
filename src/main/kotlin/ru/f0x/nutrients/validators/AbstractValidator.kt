package ru.f0x.nutrients.validators

import ru.f0x.nutrients.exceptions.NotAcceptableDataException
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидация объекта целеком а не по полям используется потому что баг https://youtrack.jetbrains.com/issue/KT-13228
 * */
abstract class AbstractValidator<A : Annotation, DTO : Any> : ConstraintValidator<A, DTO> {
    companion object {
        const val SHOULD_BE_NOT_EMPTY_MESSAGE = "should be not empty"
        const val SHOULD_BE_ABOVE_MESSAGE = "should be above empty %d"
        const val ALREADY_EXIST_MESSAGE = "%s already exist"
    }

    override fun isValid(value: DTO?, context: ConstraintValidatorContext?): Boolean {
        if (value == null)
            return false
        return validate(value)
    }

    protected fun errorsMap() = mutableMapOf<String, Any>()

    @Throws(NotAcceptableDataException::class)
    protected open fun successResultOrException(errors: Map<String, Any>, dto: DTO): Boolean {
        if (errors.isEmpty())
            return true
        throw NotAcceptableDataException(dto, errors)
    }

    abstract fun validate(dto: DTO): Boolean
}