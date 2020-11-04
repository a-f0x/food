package ru.f0x.nutrients.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.f0x.nutrients.models.entity.NutrientEntity

interface NutrientRepository : JpaRepository<NutrientEntity, Int> {

    @Query("SELECT n from NutrientEntity as n WHERE LOWER(n.name) LIKE LOWER (CONCAT ('%',:name,'%'))")
    fun findAllByName(name: String): List<NutrientEntity>

    @Query("SELECT n from NutrientEntity as n WHERE LOWER(n.manufacturer) LIKE LOWER (CONCAT ('%',:manufacturer,'%'))")
    fun findAllByManufacturer(manufacturer: String): List<NutrientEntity>

    fun deleteAllByIdIn(ids: List<Int>)
}