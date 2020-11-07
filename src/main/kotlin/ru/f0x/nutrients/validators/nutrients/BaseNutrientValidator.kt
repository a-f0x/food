package ru.f0x.nutrients.validators.nutrients

import ru.f0x.nutrients.models.dto.nutrients.NutrientDTO
import ru.f0x.nutrients.repository.NutrientRepository
import ru.f0x.nutrients.validators.AbstractValidator
import ru.f0x.nutrients.validators.SHOULD_BE_ABOVE_MESSAGE
import ru.f0x.nutrients.validators.SHOULD_BE_NOT_EMPTY_MESSAGE

abstract class BaseNutrientValidator<A : Annotation, DTO : NutrientDTO>(
        protected val repository: NutrientRepository
) : AbstractValidator<A, DTO>() {

    protected fun validateNutrient(dto: DTO): Boolean {

        val errors = errorsMap()
        if (dto.name.isEmpty())
            errors["name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.kCal <= 0)
            errors["kilocalories"] = String.format(SHOULD_BE_ABOVE_MESSAGE, 0)

        return successResultOrException(errors, dto)
    }
}