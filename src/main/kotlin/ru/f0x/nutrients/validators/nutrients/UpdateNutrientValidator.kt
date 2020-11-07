package ru.f0x.nutrients.validators.nutrients

import ru.f0x.nutrients.exceptions.NutrientNotFoundException
import ru.f0x.nutrients.models.dto.nutrients.UpdateNutrientDTO
import ru.f0x.nutrients.repository.NutrientRepository
import ru.f0x.nutrients.validators.SHOULD_BE_NOT_EMPTY_MESSAGE
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

class UpdateNutrientValidator(repository: NutrientRepository) : BaseNutrientValidator<CorrectUpdateNutrient, UpdateNutrientDTO>(repository) {

    override fun validate(dto: UpdateNutrientDTO): Boolean {
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