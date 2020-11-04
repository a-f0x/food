package ru.f0x.nutrients.services

import ru.f0x.nutrients.models.dto.NutrientDTO

interface INutrientService {
    fun findByName(name: String): List<NutrientDTO>
    fun findByManufacturer(manufacturer: String): List<NutrientDTO>
    fun add(nutrient: NutrientDTO): NutrientDTO
    fun addAll(nutrients: List<NutrientDTO>): List<NutrientDTO>
}