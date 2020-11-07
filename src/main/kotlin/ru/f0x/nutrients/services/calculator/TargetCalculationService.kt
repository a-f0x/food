package ru.f0x.nutrients.services.calculator

import org.springframework.stereotype.Service
import ru.f0x.nutrients.models.dto.CustomUserDetails
import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResultDTO
import ru.f0x.nutrients.services.users.IUserService

@Service
class TargetCalculationService(private val userService: IUserService) : ITargetCalculationService {

    override fun calculate(user: CustomUserDetails): TargetCalculationResultDTO {
        val profile = userService.getUserProfile(user).profile!!
        return BaseTargetCalculator(
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.sex
        ).calculate()
    }

}