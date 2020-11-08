package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.calculator.CalculationResult

interface ITargetCalculationService {

    fun calculate(user: CustomUserDetails): CalculationResult

}