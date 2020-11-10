package ru.f0x.food.services.food

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.models.entity.FoodEntity

@Component
class FoodMapper {

    fun <T : FoodProductDTO> mapFromDTO(dto: T): FoodEntity {

        return FoodEntity().apply {
            id = dto.id ?: 0
            name = dto.name
            carb = dto.carb
            protein = dto.protein
            fat = dto.fat
            kCal = dto.kCal
        }
    }

    fun mapFromEntity(entity: FoodEntity): FoodProductDTO {
        return FoodProductDTO(
                entity.id,
                entity.name,
                entity.carb,
                entity.protein,
                entity.fat,
                entity.kCal
        )
    }
}