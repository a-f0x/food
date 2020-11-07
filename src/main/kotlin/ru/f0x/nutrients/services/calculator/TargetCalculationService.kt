package ru.f0x.nutrients.services.calculator

import org.springframework.stereotype.Service
import ru.f0x.nutrients.models.dto.CustomUserDetails
import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResult
import ru.f0x.nutrients.models.entity.ActivityEnum
import ru.f0x.nutrients.models.entity.SexEnum
import ru.f0x.nutrients.models.entity.TargetEnum
import ru.f0x.nutrients.services.users.IUserService

@Service
class TargetCalculationService(private val userService: IUserService) : ITargetCalculationService {

    override fun calculate(user: CustomUserDetails): TargetCalculationResult {
        val profile = userService.getUserProfile(user).profile!!
        val cfg = CalculatorConfiguration(
                profile.sex,
                profile.weight,
                profile.height,
                profile.age,
                profile.activity,
                profile.target
        )
        return createCalculator(cfg).calculate()
    }

    private fun createCalculator(configuration: CalculatorConfiguration): ITargetCalculator {
        return when (configuration.sex) {
            SexEnum.FEMALE -> createFemaleCalculator(configuration)
            SexEnum.MALE -> createMaleCalculator(configuration)
        }
    }

    private fun createFemaleCalculator(configuration: CalculatorConfiguration): ITargetCalculator {
        return when (configuration.target) {
            TargetEnum.GAIN_WEIGHT -> FemaleGainWeightTargetCalculator(
                    configuration.weight,
                    configuration.height,
                    configuration.age,
                    configuration.activity
            )
            TargetEnum.LOSE_WEIGHT -> FemaleLoseWeightTargetCalculator(
                    configuration.weight,
                    configuration.height,
                    configuration.age,
                    configuration.activity
            )
            TargetEnum.SAVE_WEIGHT -> FemaleSaveWeightTargetCalculator(
                    configuration.weight,
                    configuration.height,
                    configuration.age,
                    configuration.activity
            )
        }
    }

    private fun createMaleCalculator(configuration: CalculatorConfiguration): ITargetCalculator {
        return when (configuration.target) {
            TargetEnum.GAIN_WEIGHT -> MaleGainWeightTargetCalculator(
                    configuration.weight,
                    configuration.height,
                    configuration.age,
                    configuration.activity
            )
            TargetEnum.LOSE_WEIGHT -> MaleLoseWeightTargetCalculator(
                    configuration.weight,
                    configuration.height,
                    configuration.age,
                    configuration.activity
            )
            TargetEnum.SAVE_WEIGHT -> MaleSaveWeightTargetCalculator(
                    configuration.weight,
                    configuration.height,
                    configuration.age,
                    configuration.activity
            )
        }
    }

    private data class CalculatorConfiguration(
            val sex: SexEnum,
            val weight: Float,
            val height: Float,
            val age: Int,
            val activity: ActivityEnum,
            val target: TargetEnum
    )
}