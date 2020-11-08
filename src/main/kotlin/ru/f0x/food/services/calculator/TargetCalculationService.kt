package ru.f0x.food.services.calculator

import org.springframework.stereotype.Service
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.calculator.CalculationResult
import ru.f0x.food.services.users.IUserService

@Service
class TargetCalculationService(private val userService: IUserService) : ITargetCalculationService {

    override fun calculate(user: CustomUserDetails): CalculationResult {
        val profile = userService.getUserProfile(user).profile!!
        return TargetCalculator(
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.sex
        ).calculate(profile.target)
    }

}