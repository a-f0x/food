package ru.f0x.food.services.users

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.users.UserDTO
import ru.f0x.food.models.dto.users.UserProfileDTO
import ru.f0x.food.models.entity.RoleEntity
import ru.f0x.food.models.entity.UserEntity
import ru.f0x.food.models.entity.UserProfileEntity
import java.time.LocalDateTime

@Component
class UserMapper {

    fun mapFromEntity(userEntity: UserEntity, profileEntity: UserProfileEntity, password: String?): UserDTO = UserDTO(
            id = userEntity.id,
            password = password,
            email = userEntity.email,
            profile = UserProfileDTO(
                    profileEntity.id,
                    profileEntity.sex,
                    profileEntity.weight,
                    profileEntity.height,
                    profileEntity.age,
                    profileEntity.activity,
                    profileEntity.target
            )
    )

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
}