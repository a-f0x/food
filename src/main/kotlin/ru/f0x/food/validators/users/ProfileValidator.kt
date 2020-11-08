package ru.f0x.food.validators.users

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.users.UserProfileDTO
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.validators.SHOULD_BE_BETWEEN_IN

@Component
class ProfileValidator(private val service: IUserService) {
    private companion object {
        private val ageRange = (18..100)
        private val heightRange = (150f..220f)
        private val weightRange = (40f..170f)
    }

    fun <T : UserProfileDTO> validate(profile: T): MutableMap<String, Any> {
        val errors = mutableMapOf<String, Any>()
        if (profile.age !in ageRange)
            errors["age"] = String.format(SHOULD_BE_BETWEEN_IN, ageRange.first, ageRange.last)

        if (profile.height !in heightRange)
            errors["height"] = String.format(SHOULD_BE_BETWEEN_IN, heightRange.start.toInt(), heightRange.endInclusive.toInt())

        if (profile.weight !in weightRange)
            errors["weight"] = String.format(SHOULD_BE_BETWEEN_IN, weightRange.start.toInt(), weightRange.endInclusive.toInt())

        return errors
    }

}