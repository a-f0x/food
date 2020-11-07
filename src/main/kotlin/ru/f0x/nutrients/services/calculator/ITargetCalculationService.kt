package ru.f0x.nutrients.services.calculator

import ru.f0x.nutrients.models.dto.CustomUserDetails
import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResult

interface ITargetCalculationService {

    fun calculate(user: CustomUserDetails): TargetCalculationResult

}