package ru.f0x.food.services.users

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.Profile
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

    override fun isLoginExist(login: String): Boolean = usersRepository.existsByLogin(login)

    @Transactional
    override fun registerUser(createUserDTO: CreateUserDTO): Profile {
        val currentTime = getCurrentTime()
        val password = createUserDTO.password
        val user = usersRepository.save(
                mapper.mapFromDTO(
                        createUserDTO,
                        passwordEncoder.encode(password),
                        currentTime,
                        userRoles.first { it.role == RoleEnum.USER }
                )
        )
        val profile = profileRepository.save(
                mapper.mapFromDTO(createUserDTO.profile, currentTime, user.id)
        )
        return mapper.mapFromEntity(profile)
    }

    override fun getUserProfile(user: CustomUserDetails): Profile {
        val profile = profileRepository.findByUserId(user.id)
        return mapper.mapFromEntity(profile)
    }

    override fun getUserByLogin(login: String): Profile? {
        val user = usersRepository.findByLogin(login) ?: return null
        val profile = profileRepository.findByUserId(user.id)
        return mapper.mapFromEntity(profile)
    }


    private fun getCurrentTime() = dateTimeService.getCurrentTime()
}