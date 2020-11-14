package ru.f0x.food.repository.events

import org.springframework.data.repository.CrudRepository
import ru.f0x.food.models.entity.events.FoodEventEntity

interface FoodEventRepository : CrudRepository<FoodEventEntity, Int> {

}