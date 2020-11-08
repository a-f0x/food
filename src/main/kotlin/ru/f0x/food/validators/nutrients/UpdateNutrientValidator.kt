package ru.f0x.food.validators.nutrients

import ru.f0x.food.exceptions.NutrientNotFoundException
import ru.f0x.food.models.dto.food.UpdateFoodProductDTO
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.validators.SHOULD_BE_NOT_EMPTY_MESSAGE
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UpdateNutrientValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectUpdateNutrient(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class UpdateNutrientValidator(repository: FoodRepository) : BaseNutrientValidator<CorrectUpdateNutrient, UpdateFoodProductDTO>(repository) {

    override fun validate(dto: UpdateFoodProductDTO): Boolean {
        if (dto.id == 0)
            return successResultOrException(
                    mapOf("id" to SHOULD_BE_NOT_EMPTY_MESSAGE),
                    dto
            )
        val exist = repository.existsById(dto.id)
        if (exist.not())
            throw NutrientNotFoundException(dto.id)
        validateNutrient(dto)
        return true
    }

}