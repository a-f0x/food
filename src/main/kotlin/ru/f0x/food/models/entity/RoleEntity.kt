package ru.f0x.food.models.entity

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "roles")
class RoleEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var roleId: Int = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 64, nullable = false)
    lateinit var role: RoleEnum
}