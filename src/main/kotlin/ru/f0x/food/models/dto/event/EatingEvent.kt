package ru.f0x.food.models.dto.event

import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.models.entity.EventTypeEnum

data class EatingEvent(
        private val food: FoodProductDTO,
        private val weightGram: Float
) : IEvent {
    override val canCalc: Boolean = true
    override val type: EventTypeEnum = EventTypeEnum.EATING

//    override fun calculate(): NutrientsResult {
//        return NutrientsResult(
//                Nutrient(Protein)
//        )
//    }
}