package ru.f0x.food.validators.nutrients

import ru.f0x.food.models.dto.food.CreateFoodProductDTO
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.validators.ALREADY_EXIST_MESSAGE
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateNutrientValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectCreateNutrient(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class CreateNutrientValidator(repository: FoodRepository)
    : BaseNutrientValidator<CorrectCreateNutrient, CreateFoodProductDTO>(repository) {

    override fun validate(dto: CreateFoodProductDTO): Boolean {
        validateNutrient(dto)
        val nutrient = repository.findAllByName(dto.name)
        if (nutrient.isNotEmpty())
            return successResultOrException(
                    mapOf("name" to ALREADY_EXIST_MESSAGE),
                    dto
            )
        return true
    }

}