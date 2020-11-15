package ru.f0x.food.models.dto.users

import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum
import ru.f0x.food.models.entity.TargetEnum

data class ProfileResponse(
        val telegramId: Int?,
        val password: String?,
        val email: String,
        val sex: SexEnum,
        val weight: Float,
        val height: Float,
        val age: Int,
        val activity: ActivityEnum,
        val target: TargetEnum,
        val basicKiloCalories: Float,
        val totalKiloCalories: Float,
        val nutrients: Map<String, NutrientsResponse>
)