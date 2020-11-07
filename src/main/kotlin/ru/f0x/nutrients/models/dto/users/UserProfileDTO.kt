package ru.f0x.nutrients.models.dto.users

import ru.f0x.nutrients.models.entity.ActivityEnum
import ru.f0x.nutrients.models.entity.SexEnum

open class UserProfileDTO(
        open val id: Int?,
        val sex: SexEnum,
        val weight: Float,
        val height: Float,
        val age: Int,
        val activity: ActivityEnum,
)