package ru.f0x.nutrients.models.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user_profile")
class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    /**
     * foreign_key на таблицу [UserEntity]
     * */
    @Column(name = "user_id", nullable = false)
    open var userId: Int = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 16, nullable = false)
    open lateinit var sex: SexEnum

    @Column(name = "weight", nullable = false)
    var weight: Float = 0f

    @Column(name = "height", nullable = false)
    var height: Float = 0f

    @Column(name = "age", nullable = false)
    var age: Int = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "activity", length = 32, nullable = false)
    open lateinit var activity: ActivityEnum

    @Enumerated(EnumType.STRING)
    @Column(name = "target", length = 32, nullable = false)
    open lateinit var target: TargetEnum

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    open lateinit var created: LocalDateTime

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    open lateinit var modified: LocalDateTime

}