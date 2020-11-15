package ru.f0x.food.validators.food

import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.validators.AbstractConstraintValidator

abstract class BaseFoodConstraintValidator<A : Annotation, DTO : FoodProductDTO>(
        private val baseValidator: FoodValidator,
        protected val repository: FoodRepository
) : AbstractConstraintValidator<A, DTO>(baseValidator) {

    protected fun validateNutrient(dto: DTO): Boolean {
        return baseValidator.validateNutrient(dto)
    }
}