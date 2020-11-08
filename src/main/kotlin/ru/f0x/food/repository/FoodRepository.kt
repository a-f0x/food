package ru.f0x.food.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.f0x.food.models.entity.FoodEntity

interface FoodRepository : JpaRepository<FoodEntity, Int> {

    @Query("SELECT * FROM  food WHERE  name ILIKE %:name%", nativeQuery = true)
    fun findAllByName(@Param("name") name: String): List<FoodEntity>

    @Query("SELECT * FROM  food WHERE  manufacturer ILIKE %:manufacturer%", nativeQuery = true)
    fun findAllByManufacturer(@Param("manufacturer") manufacturer: String): List<FoodEntity>

    fun deleteAllByIdIn(ids: List<Int>)

    @Query("SELECT * FROM  food WHERE LOWER(name) = LOWER(:name)", nativeQuery = true)
    fun findByName(@Param("name") name: String): FoodEntity?
}