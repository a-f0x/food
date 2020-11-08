package ru.f0x.food.services.users

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.users.CreateUserDTO
import ru.f0x.food.models.dto.users.NutrientsResponse
import ru.f0x.food.models.dto.users.ProfileResponse
import ru.f0x.food.models.entity.RoleEntity
import ru.f0x.food.models.entity.RoleEnum
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.repository.RolesRepository
import ru.f0x.food.repository.UserProfileRepository
import ru.f0x.food.repository.UsersRepository
import ru.f0x.food.services.IDateTimeService
import ru.f0x.food.services.calculator.TargetCalculator

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
        val result = calculateNutrients(profile)

        return ProfileResponse(
                password,
                user.email,
                profile.sex,
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.target,
                result.basicKCal,
                result.totalKCal,
                result.nutrientResponse
        )
    }

    override fun getUserProfile(user: CustomUserDetails): ProfileResponse {
        val profile = profileRepository.findByUserId(user.id)
        val result = calculateNutrients(profile)
        return ProfileResponse(
                null,
                user.email,
                profile.sex,
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.target,
                result.basicKCal,
                result.totalKCal,
                result.nutrientResponse
        )

    }

    private fun calculateNutrients(profile: UserProfileEntity): CalcResult {
        val result = TargetCalculator(
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.sex
        ).calculate(profile.target)

        val proteins = NutrientsResponse(
                result.nutrients.proteins.name,
                result.nutrients.proteins.weightGram,
                result.nutrients.proteins.kCal,
                result.nutrients.proteins.type.kCalPerGram
        )
        val fats = NutrientsResponse(
                result.nutrients.fats.name,
                result.nutrients.fats.weightGram,
                result.nutrients.fats.kCal,
                result.nutrients.fats.type.kCalPerGram
        )
        val carbohydrates = NutrientsResponse(
                result.nutrients.carbohydrates.name,
                result.nutrients.carbohydrates.weightGram,
                result.nutrients.carbohydrates.kCal,
                result.nutrients.carbohydrates.type.kCalPerGram
        )

        return CalcResult(
                result.basicKCal,
                result.nutrients.totalKCal,
                mapOf(
                        proteins.name to proteins,
                        fats.name to fats,
                        carbohydrates.name to carbohydrates
                )
        )
    }

    private fun getCurrentTime() = dateTimeService.getCurrentTime()

    private data class CalcResult(
            val basicKCal: Float,
            val totalKCal: Float,
            val nutrientResponse: Map<String, NutrientsResponse>

    )

}