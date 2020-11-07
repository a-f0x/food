package ru.f0x.nutrients.services.users

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.nutrients.models.dto.users.CreateUserDTO
import ru.f0x.nutrients.models.dto.users.UserDTO
import ru.f0x.nutrients.models.dto.users.UserProfileDTO
import ru.f0x.nutrients.models.entity.RoleEntity
import ru.f0x.nutrients.models.entity.UserEntity
import ru.f0x.nutrients.models.entity.UserProfileEntity
import ru.f0x.nutrients.repository.RolesRepository
import ru.f0x.nutrients.repository.UserProfileRepository
import ru.f0x.nutrients.repository.UsersRepository
import ru.f0x.nutrients.services.IDateTimeService

@Service
class UserService(
        rolesRepository: RolesRepository,
        private val usersRepository: UsersRepository,
        private val profileRepository: UserProfileRepository,
        private val dateTimeService: IDateTimeService,
        private val mapper: UserMapper
) : IUserService {
    private val passwordEncoder = BCryptPasswordEncoder()
    private val userRoles: MutableSet<RoleEntity> = mutableSetOf()

    init {
        userRoles.addAll(rolesRepository.findAll())
    }


    override fun isEmailExist(email: String): Boolean {
        return usersRepository.existsByEmail(email)
    }

    @Transactional
    override fun registerUser(createUserDTO: CreateUserDTO): UserDTO {
        val currentTime = getCurrentTime()
        val u = usersRepository.save(
                UserEntity().apply {
                    this.email = createUserDTO.email
                    this.pwd = passwordEncoder.encode(createUserDTO.password)
                    this.created = currentTime
                    this.modified = currentTime
                    this.roles = mutableSetOf(
                            findRole(RoleEntity.USER)
                    )
                }
        )

        val p = profileRepository.save(
                UserProfileEntity().apply {
                    this.sex = createUserDTO.profile.sex
                    this.weight = createUserDTO.profile.weight
                    this.height = createUserDTO.profile.height
                    this.age = createUserDTO.profile.age
                    this.activity = createUserDTO.profile.activity
                    this.created = currentTime
                    this.modified = currentTime
                    this.userId = u.id
                }
        )
        return UserDTO(
                id = u.id,
                password = createUserDTO.password,
                email = createUserDTO.email,
                profile = UserProfileDTO(
                        p.id,
                        p.sex,
                        p.weight,
                        p.height,
                        p.age,
                        p.activity
                )
        )
    }


    private fun findRole(roleName: String): RoleEntity {
        return userRoles.first { it.role == roleName }
    }

    private fun getCurrentTime() = dateTimeService.getCurrentTime()

}