package ru.f0x.food.services.food

import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.food.FoodProductDTO

interface IFoodProductsService {

    fun findByName(name: String): List<FoodProductDTO>

    fun add(user: CustomUserDetails, foodProduct: FoodProductDTO): FoodProductDTO

    fun addAll(user: CustomUserDetails, foodProducts: List<FoodProductDTO>): List<FoodProductDTO>

    fun getAll(): List<FoodProductDTO>

    fun update(user: CustomUserDetails, foodProductDTO: FoodProductDTO): FoodProductDTO

    fun delete(ids: List<Int>)
}