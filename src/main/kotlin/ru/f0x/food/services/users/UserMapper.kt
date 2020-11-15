package ru.f0x.food.services.users

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.users.NutrientsResponse
import ru.f0x.food.models.dto.users.ProfileResponse
import ru.f0x.food.models.dto.users.UserDTO
import ru.f0x.food.models.dto.users.UserProfileDTO
import ru.f0x.food.models.entity.RoleEntity
import ru.f0x.food.models.entity.UserEntity
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.services.calculator.calculateTargetForUserProfile
import java.time.LocalDateTime

@Component
class UserMapper {

    fun mapFromEntity(profile: UserProfileEntity, email: String, password: String?, telegramId: Int?): ProfileResponse {
        val result = calculateNutrients(profile)
        return ProfileResponse(
                telegramId,
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
        val result = profile.calculateTargetForUserProfile()

        val proteins = NutrientsResponse(
                result.nutrients.protein.name,
                result.nutrients.protein.weightGram,
                result.nutrients.protein.kCal,
                result.nutrients.protein.kCalPerGram
        )
        val fats = NutrientsResponse(
                result.nutrients.fat.name,
                result.nutrients.fat.weightGram,
                result.nutrients.fat.kCal,
                result.nutrients.fat.kCalPerGram
        )
        val carbohydrates = NutrientsResponse(
                result.nutrients.carb.name,
                result.nutrients.carb.weightGram,
                result.nutrients.carb.kCal,
                result.nutrients.carb.kCalPerGram
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