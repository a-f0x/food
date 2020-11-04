package ru.f0x.nutrients.services

import org.springframework.stereotype.Component
import ru.f0x.nutrients.models.dto.NutrientDTO
import ru.f0x.nutrients.models.entity.NutrientEntity

@Component
class NutrientMapper {

    fun <T : NutrientDTO> mapFromDTO(dto: T): NutrientEntity {
        return NutrientEntity().apply {
            id = dto.id ?: 0
            name = dto.name
            manufacturer = dto.manufacturer
            carbohydrates = dto.carbohydrates
            proteins = dto.proteins
            fats = dto.fats
            kilocalories = dto.kilocalories
        }
    }

    fun mapFromEntity(entity: NutrientEntity): NutrientDTO {
        return NutrientDTO(
                entity.id,
                entity.name,
                entity.manufacturer,
                entity.carbohydrates,
                entity.proteins,
                entity.fats,
                entity.kilocalories
        )
    }
}