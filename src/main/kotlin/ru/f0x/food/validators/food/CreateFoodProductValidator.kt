package ru.f0x.food.validators.food

import ru.f0x.food.models.dto.food.CreateFoodProductDTO
import ru.f0x.food.repository.FoodRepository
import ru.f0x.food.validators.ALREADY_EXIST_MESSAGE
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateFoodProductValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectCreateFoodProduct(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class CreateFoodProductValidator(repository: FoodRepository)
    : BaseFoodValidator<CorrectCreateFoodProduct, CreateFoodProductDTO>(repository) {

    override fun validate(dto: CreateFoodProductDTO): Boolean {
        validateNutrient(dto)
        val foodProduct = repository.findByName(dto.name)
        if (foodProduct != null)
            return successResultOrException(
                    mapOf("name" to ALREADY_EXIST_MESSAGE),
                    dto
            )
        return true
    }

}