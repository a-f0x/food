package ru.f0x.food.validators.food

import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.validators.AbstractValidator
import ru.f0x.food.validators.SHOULD_BE_ABOVE_MESSAGE
import ru.f0x.food.validators.SHOULD_BE_NOT_EMPTY_MESSAGE

abstract class BaseFoodValidator<A : Annotation, DTO : FoodProductDTO>(
        protected val repository: FoodRepository
) : AbstractValidator<A, DTO>() {

    protected fun validateNutrient(dto: DTO): Boolean {

        val errors = errorsMap()
        if (dto.name.isEmpty())
            errors["name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.kiloCal <= 0)
            errors["kilo_cal"] = String.format(SHOULD_BE_ABOVE_MESSAGE, 0)

        return successResultOrException(errors, dto)
    }
}