package ru.f0x.food.models.dto.users

import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum
import ru.f0x.food.models.entity.TargetEnum

open class UserProfileDTO(
        open val id: Int?,
        val sex: SexEnum,
        val weight: Float,
        val height: Float,
        val age: Int,
        val activity: ActivityEnum,
        val target: TargetEnum,
        val gmt: Float = 7.0f
)