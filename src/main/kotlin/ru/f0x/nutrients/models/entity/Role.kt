package ru.f0x.nutrients.models.entity

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "roles")
class Role : Serializable {

    companion object {
        const val ADMIN = "ADMIN"
        const val USER = "USER"
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var roleId: Int = 0

    @Column(name = "role")
    var role: String? = null
}