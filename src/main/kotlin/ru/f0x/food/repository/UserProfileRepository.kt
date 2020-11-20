package ru.f0x.food.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.telegram.cases.CaseType

interface UserProfileRepository : CrudRepository<UserProfileEntity, Int> {
    fun findByUserId(userId: Int): UserProfileEntity


    @Modifying
    @Query(
            "UPDATE UserProfileEntity p SET p.case= :case WHERE p.id = :id"
    )
    fun setCase(@Param("id") profileId: Int, @Param("case") case: CaseType)
}