package ru.f0x.nutrients.validators

import ru.f0x.nutrients.models.dto.UpdateNutrientDTO
import ru.f0x.nutrients.services.INutrientService
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

class UpdateNutrientValidator(private val service: INutrientService) : BaseNutrientValidator<CorrectUpdateNutrient, UpdateNutrientDTO>() {

    override fun validate(dto: UpdateNutrientDTO): Boolean {
        TODO("Not yet implemented")
    }

}