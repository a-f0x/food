package ru.f0x.food.services.users

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.users.NutrientsResponse
import ru.f0x.food.models.dto.users.ProfileResponse
import ru.f0x.food.models.dto.users.UserDTO
import ru.f0x.food.models.dto.users.UserProfileDTO
import ru.f0x.food.models.entity.RoleEntity
import ru.f0x.food.models.entity.UserEntity
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.services.calculator.TargetCalculator
import java.time.LocalDateTime

@Component
class UserMapper {

    fun mapFromEntity(profile: UserProfileEntity, email: String, password: String?): ProfileResponse {
        val result = calculateNutrients(profile)
        return ProfileResponse(
                password,
                email,
                profile.sex,
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.target,
                result.basicKCal,
                result.totalKCal,
                result.nutrientResponse
        )

    }

    fun mapFromDTO(dto: UserDTO, password: String, time: LocalDateTime, role: RoleEntity): UserEntity =
            UserEntity().apply {
                this.email = dto.email
                this.pwd = password
                this.created = time
                this.modified = time
                this.roles = mutableSetOf(role)
            }

    fun mapFromDTO(profile: UserProfileDTO, time: LocalDateTime, userId: Int): UserProfileEntity =
            UserProfileEntity().apply {
                this.userId = userId
                this.sex = profile.sex
                this.weight = profile.weight
                this.height = profile.height
                this.age = profile.age
                this.activity = profile.activity
                this.target = profile.target
                this.created = time
                this.modified = time
            }


    private fun calculateNutrients(profile: UserProfileEntity): CalcResult {
        val result = TargetCalculator(
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.sex
        ).calculate(profile.target)

        val proteins = NutrientsResponse(
                result.nutrients.proteins.name,
                result.nutrients.proteins.weightGram,
                result.nutrients.proteins.kCal,
                result.nutrients.proteins.kCalPerGram
        )
        val fats = NutrientsResponse(
                result.nutrients.fats.name,
                result.nutrients.fats.weightGram,
                result.nutrients.fats.kCal,
                result.nutrients.fats.kCalPerGram
        )
        val carbohydrates = NutrientsResponse(
                result.nutrients.carbohydrates.name,
                result.nutrients.carbohydrates.weightGram,
                result.nutrients.carbohydrates.kCal,
                result.nutrients.carbohydrates.kCalPerGram
        )

        return CalcResult(
                result.basicKCal,
                result.nutrients.totalKCal,
                mapOf(
                        proteins.name to proteins,
                        fats.name to fats,
                        carbohydrates.name to carbohydrates
                )
        )
    }

    private data class CalcResult(
            val basicKCal: Float,
            val totalKCal: Float,
            val nutrientResponse: Map<String, NutrientsResponse>

    )
}