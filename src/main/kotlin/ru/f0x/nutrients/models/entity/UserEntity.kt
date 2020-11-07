package ru.f0x.nutrients.models.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "users")
open class UserEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Int = 0

    @Column(name = "login", unique = true, nullable = false)
    open lateinit var email: String

    @Column(name = "password", nullable = false)
    open lateinit var pwd: String

    @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id")])
    open var roles: Set<RoleEntity>? = null

    constructor()

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    open lateinit var created: LocalDateTime

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    open lateinit var modified: LocalDateTime

    constructor(users: UserEntity) {
        this.id = users.id
        this.email = users.email
        this.pwd = users.pwd
        this.roles = users.roles
    }

    override fun toString(): String {
        return "User(id=$id, name=$email,  roles=$roles)"
    }


}