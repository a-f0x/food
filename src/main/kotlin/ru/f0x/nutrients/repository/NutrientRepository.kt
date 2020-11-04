package ru.f0x.nutrients.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.f0x.nutrients.models.entity.NutrientEntity

interface NutrientRepository : JpaRepository<NutrientEntity, Int> {

    fun findAllByName(name: String): List<NutrientEntity>

    fun findAllByManufacturer(manufacturer: String): List<NutrientEntity>
}