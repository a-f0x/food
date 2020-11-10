package ru.f0x.food.validators

import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.validators.food.BaseValidator
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидация объекта целеком а не по полям используется потому что баг https://youtrack.jetbrains.com/issue/KT-13228
 * */
abstract class AbstractConstraintValidator<A : Annotation, DTO : Any>(private val baseValidator: BaseValidator) : ConstraintValidator<A, DTO> {

    override fun isValid(value: DTO?, context: ConstraintValidatorContext?): Boolean {
        if (value == null)
            return false
        return validate(value)
    }

    protected fun errorsMap() = baseValidator.errorsMap()

    @Throws(NotAcceptableDataException::class)
    protected open fun successResultOrException(errors: Map<String, Any>, dto: DTO): Boolean {
        return baseValidator.successResultOrException(errors, dto)
    }

    abstract fun validate(dto: DTO): Boolean
}