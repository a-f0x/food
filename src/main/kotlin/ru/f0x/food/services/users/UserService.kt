package ru.f0x.food.services.users

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.exceptions.UserNotFoundException
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.ProfileResponse
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

    override fun isEmailExist(email: String): Boolean = usersRepository.existsByEmail(email)

    @Transactional
    override fun registerUser(createUserDTO: CreateUserDTO): ProfileResponse {
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
        return mapper.mapFromEntity(profile, user.email, password, null)
    }

    override fun getUserProfile(user: CustomUserDetails): ProfileResponse {
        val profile = profileRepository.findByUserId(user.id)
        return mapper.mapFromEntity(profile, user.email, null, null)
    }

    override fun getUserByTelegramId(telegramId: Int): ProfileResponse {
        val user = usersRepository.getByTelegramId(telegramId)
                ?: throw UserNotFoundException("User with telegram id:$telegramId not found.")
        val profile = profileRepository.findByUserId(user.id)
        return mapper.mapFromEntity(profile, user.email, null, user.telegramId)
    }

    private fun getCurrentTime() = dateTimeService.getCurrentTime()
}