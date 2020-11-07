package ru.f0x.nutrients.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.f0x.nutrients.models.entity.RoleEntity

interface RolesRepository : JpaRepository<RoleEntity, Int>