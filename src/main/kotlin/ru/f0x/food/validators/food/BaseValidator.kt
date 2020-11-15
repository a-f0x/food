package ru.f0x.food.validators.food

import org.springframework.stereotype.Component
import ru.f0x.food.exceptions.NotAcceptableDataException

@Component
open class BaseValidator {
    fun errorsMap() = mutableMapOf<String, Any>()

    @Throws(NotAcceptableDataException::class)
    fun successResultOrException(errors: Map<String, Any>, dto: Any): Boolean {
        if (errors.isEmpty())
            return true
        throw NotAcceptableDataException(dto, errors)
    }

}