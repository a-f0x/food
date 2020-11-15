package ru.f0x.food.models.dto.users

import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum
import ru.f0x.food.models.entity.TargetEnum

class CreateUserProfileDTO(
        sex: SexEnum,
        weight: Float,
        height: Float,
        age: Int,
        activity: ActivityEnum,
        target: TargetEnum,
        gmt: Float = 7.0f
) : UserProfileDTO(
        id = null,
        sex = sex,
        weight = weight,
        height = height,
        age = age,
        activity = activity,
        target = target,
        gmt
)