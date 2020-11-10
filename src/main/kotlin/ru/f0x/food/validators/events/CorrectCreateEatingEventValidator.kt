package ru.f0x.food.validators.events

import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.validators.AbstractConstraintValidator
import ru.f0x.food.validators.SHOULD_BE_ABOVE_MESSAGE
import ru.f0x.food.validators.SHOULD_BE_NOT_EMPTY_MESSAGE
import ru.f0x.food.validators.food.BaseValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateCorrectFoodEventValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CreateCorrectFoodEvent(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class CreateCorrectFoodEventValidator(baseValidator: BaseValidator)
    : AbstractConstraintValidator<CreateCorrectFoodEvent, CreateEventForFoodDTO>(baseValidator) {

    override fun validate(dto: CreateEventForFoodDTO): Boolean {
        val errors = errorsMap()

        if (dto.weightGram <= 0)
            errors["weight_gram"] = String.format(SHOULD_BE_ABOVE_MESSAGE, 0)

        if (dto.name.isEmpty())
            errors["name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        return successResultOrException(errors, dto)

    }

}