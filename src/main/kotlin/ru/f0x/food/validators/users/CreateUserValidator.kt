package ru.f0x.food.validators.users

import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.validators.AbstractConstraintValidator
import ru.f0x.food.validators.IEmailValidator
import ru.f0x.food.validators.food.BaseValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Constraint(validatedBy = [CreateUserValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectCreateUser(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])


class CreateUserValidator(
        baseValidator: BaseValidator,
        private val service: IUserService,
        private val emailValidator: IEmailValidator,
        private val profileValidator: ProfileValidator
) : AbstractConstraintValidator<CorrectCreateUser, CreateUserDTO>(baseValidator) {
    private companion object {

        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
        private const val MIN_PASSWORD_LENGTH = 6
    }

    override fun validate(dto: CreateUserDTO): Boolean {
//        if (emailValidator.validate(dto.email).not()) {
//            return successResultOrException(
//                    mapOf(EMAIL_KEY to INVALID_MESSAGE),
//                    dto
//            )
//        }
//        val isEmailExist = service.isLoginExist(dto.email)
//        if (isEmailExist)
//            return successResultOrException(
//                    mapOf(EMAIL_KEY to String.format(ALREADY_EXIST_MESSAGE, dto.email)),
//                    dto
//            )
//
//        val pwd = dto.password
//        if (pwd.isNullOrBlank() || pwd.length < MIN_PASSWORD_LENGTH) {
//            return successResultOrException(
//                    mapOf(PASSWORD_KEY to String.format(SHOULD_BE_ABOVE_MESSAGE, MIN_PASSWORD_LENGTH)),
//                    dto
//            )
//        }
//
        val errors = profileValidator.validate(dto.profile!!)
        return successResultOrException(errors, dto)
    }

}