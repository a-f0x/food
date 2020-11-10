package ru.f0x.food.validators.events

import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.validators.AbstractConstraintValidator
import ru.f0x.food.validators.SHOULD_BE_ABOVE_MESSAGE
import ru.f0x.food.validators.food.BaseValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateCorrectWorkoutEventValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CreateCorrectWorkoutEvent(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class CreateCorrectWorkoutEventValidator(baseValidator: BaseValidator)
    : AbstractConstraintValidator<CreateCorrectWorkoutEvent, CreateEventForWorkoutDTO>(baseValidator) {

    override fun validate(dto: CreateEventForWorkoutDTO): Boolean {

        if (dto.kCal <= 0) {
            return successResultOrException(
                    mapOf("k_cal" to String.format(SHOULD_BE_ABOVE_MESSAGE, 0)),
                    dto
            )
        }
        return true

    }

}