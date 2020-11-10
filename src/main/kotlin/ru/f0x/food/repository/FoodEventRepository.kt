package ru.f0x.food.repository

import org.springframework.data.repository.CrudRepository
import ru.f0x.food.models.entity.FoodEventEntity

interface FoodEventRepository : CrudRepository<FoodEventEntity, Int> {

}