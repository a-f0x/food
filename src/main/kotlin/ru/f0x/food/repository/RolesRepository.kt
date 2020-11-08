package ru.f0x.food.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.f0x.food.models.entity.RoleEntity

interface RolesRepository : JpaRepository<RoleEntity, Int>