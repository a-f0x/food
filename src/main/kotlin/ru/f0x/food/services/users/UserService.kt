package ru.f0x.food.services.users

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.UserDTO
import ru.f0x.food.models.entity.RoleEntity
import ru.f0x.food.models.entity.RoleEnum
import ru.f0x.food.repository.RolesRepository
import ru.f0x.food.repository.UserProfileRepository
import ru.f0x.food.repository.UsersRepository
import ru.f0x.food.services.IDateTimeService

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
                mapper.mapFromDTO(
                        createUserDTO,
                        passwordEncoder.encode(createUserDTO.password),
                        currentTime,
                        userRoles.first { it.role == RoleEnum.USER }
                )
        )
        val p = profileRepository.save(
                mapper.mapFromDTO(createUserDTO.profile, currentTime, u.id)
        )
        return mapper.mapFromEntity(u, p, createUserDTO.password)
    }

    override fun getUserProfile(user: CustomUserDetails): UserDTO {
        val p = profileRepository.findByUserId(user.id)
        return mapper.mapFromEntity(user, p, null)
    }


    private fun getCurrentTime() = dateTimeService.getCurrentTime()

}