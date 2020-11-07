package ru.f0x.nutrients.services.nutrients

import ru.f0x.nutrients.models.dto.CustomUserDetails
import ru.f0x.nutrients.models.dto.nutrients.NutrientDTO

interface INutrientService {

    fun findByName(name: String): List<NutrientDTO>

    fun findByManufacturer(manufacturer: String): List<NutrientDTO>

    fun add(user: CustomUserDetails, nutrient: NutrientDTO): NutrientDTO

    fun addAll(user: CustomUserDetails, nutrients: List<NutrientDTO>): List<NutrientDTO>

    fun getAll(): List<NutrientDTO>

    fun update(user: CustomUserDetails, nutrientDTO: NutrientDTO): NutrientDTO

    fun delete(ids: List<Int>)
}