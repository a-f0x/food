package ru.f0x.nutrients.validators

import ru.f0x.nutrients.models.dto.CreateNutrientDTO
import ru.f0x.nutrients.repository.NutrientRepository
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

class CreateNutrientValidator(repository: NutrientRepository)
    : BaseNutrientValidator<CorrectCreateNutrient, CreateNutrientDTO>(repository) {

    override fun validate(dto: CreateNutrientDTO): Boolean {
        validateNutrient(dto)
        val nutrient = repository.findAllByName(dto.name)
        if (nutrient.isNotEmpty())
            return successResultOrException(
                    mapOf("name" to String.format(ALREADY_EXIST_MESSAGE, dto.name)),
                    dto
            )
        return true
    }

}