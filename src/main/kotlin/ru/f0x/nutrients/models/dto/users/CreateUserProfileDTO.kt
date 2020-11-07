package ru.f0x.nutrients.models.dto.users

import ru.f0x.nutrients.models.entity.ActivityEnum
import ru.f0x.nutrients.models.entity.SexEnum

class CreateUserProfileDTO(
        sex: SexEnum,
        weight: Float,
        height: Float,
        age: Int,
        activity: ActivityEnum)
    : UserProfileDTO(
        id = null,
        sex = sex,
        weight = weight,
        height = height,
        age = age,
        activity = activity
)