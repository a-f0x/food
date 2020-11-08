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
            dto.manufacturer?.let {
                manufacturer = it
            }
            carbohydrates = dto.carbohydrates
            proteins = dto.proteins
            fats = dto.fats
            kCal = dto.kCal
        }
    }

    fun mapFromEntity(entity: FoodEntity): FoodProductDTO {
        return FoodProductDTO(
                entity.id,
                entity.name,
                entity.manufacturer,
                entity.carbohydrates,
                entity.proteins,
                entity.fats,
                entity.kCal
        )
    }
}