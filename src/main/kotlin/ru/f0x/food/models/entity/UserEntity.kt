package ru.f0x.food.models.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "users")
open class UserEntity : Serializable {
    constructor()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Int = 0

    @Column(name = "login", unique = true, nullable = false)
    open lateinit var login: String

    @Column(name = "password", nullable = false)
    open lateinit var pwd: String

    @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id")])
    open var roles: Set<RoleEntity>? = null



    @Column(name = "created", columnDefinition = "TIMESTAMP")
    open lateinit var created: LocalDateTime

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    open lateinit var modified: LocalDateTime

    constructor(users: UserEntity) {
        this.id = users.id
        this.login = users.login
        this.pwd = users.pwd
        this.roles = users.roles
    }
}