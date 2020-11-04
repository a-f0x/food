package ru.f0x.nutrients.validators

import ru.f0x.nutrients.models.dto.NutrientDTO
import ru.f0x.nutrients.repository.NutrientRepository

abstract class BaseNutrientValidator<A : Annotation, DTO : NutrientDTO>(
        protected val repository: NutrientRepository
) : AbstractValidator<A, DTO>() {

    protected fun validateNutrient(dto: DTO): Boolean {

        val errors = errorsMap()
        if (dto.name.isEmpty())
            errors["name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.manufacturer.isBlank())
            errors["manufacturer"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.kilocalories <= 0)
            errors["address"] = String.format(SHOULD_BE_ABOVE_MESSAGE, 0)
        return successResultOrException(errors, dto)
    }
}