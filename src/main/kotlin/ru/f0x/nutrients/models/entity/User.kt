package ru.f0x.nutrients.models.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "users")
open class User : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Int = 0

    @Column(name = "login", unique = true, nullable = false)
    open lateinit var name: String

    @Column(name = "password", nullable = false)
    open lateinit var pwd: String

    @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id")])
    open var roles: Set<Role>? = null

    constructor()

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    open lateinit var creationDate: LocalDateTime

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    open lateinit var modificationDate: LocalDateTime

    constructor(users: User) {
        this.id = users.id
        this.name = users.name
        this.pwd = users.pwd
        this.roles = users.roles
    }

    override fun toString(): String {
        return "User(id=$id, name=$name,  roles=$roles)"
    }


}