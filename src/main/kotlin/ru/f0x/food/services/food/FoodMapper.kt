package ru.f0x.food.services.food

import org.springframework.stereotype.Component
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.models.entity.FoodEntity
import ru.f0x.food.services.IDateTimeService

@Component
class FoodMapper(
        private val dateTimeService: IDateTimeService
) {

    fun <T : FoodProductDTO> mapFromDTO(dto: T): FoodEntity {

        val currentTime = getCurrentTime()
        return FoodEntity().apply {
            id = dto.id ?: 0
            name = dto.name
            dto.manufacturer?.let {
                manufacturer = it
            }
            carbohydrates = dto.carbohydrates
            proteins = dto.proteins
            fats = dto.fats
            kCal = dto.kiloCal
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

    private fun getCurrentTime() = dateTimeService.getCurrentTime()
}