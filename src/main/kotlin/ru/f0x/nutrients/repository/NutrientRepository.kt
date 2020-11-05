package ru.f0x.nutrients.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.f0x.nutrients.models.entity.NutrientEntity

interface NutrientRepository : JpaRepository<NutrientEntity, Int> {

    @Query("SELECT * FROM  nutrients where name ILIKE %:name%", nativeQuery = true)
    fun findAllByName(@Param("name") name: String): List<NutrientEntity>

    @Query("SELECT * FROM  nutrients where manufacturer ILIKE %:manufacturer%", nativeQuery = true)
    fun findAllByManufacturer(@Param("manufacturer") manufacturer: String): List<NutrientEntity>

    fun deleteAllByIdIn(ids: List<Int>)
}