package ru.f0x.food.validators.food

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.validators.SHOULD_BE_ABOVE_MESSAGE
import ru.f0x.food.validators.SHOULD_BE_NOT_EMPTY_MESSAGE

@Component
class FoodValidator : BaseValidator() {

    fun <DTO : FoodProductDTO> validateNutrient(dto: DTO): Boolean {

        val errors = errorsMap()
        if (dto.name.isEmpty())
            errors["name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.kCal < 0)
            errors["k_cal"] = String.format(SHOULD_BE_ABOVE_MESSAGE, 0)

        return successResultOrException(errors, dto)
    }

}