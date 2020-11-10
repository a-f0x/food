package ru.f0x.food.models.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "food")
class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    /**
     * foreign_key на таблицу [UserEntity]
     * */
    @Column(name = "created_by_user_id", nullable = false)
    open var createdByuserId: Int = 0

    /**
     * foreign_key на таблицу [UserEntity]
     * */
    @Column(name = "modified_by_user_id", nullable = false)
    open var modifiedByuserId: Int = 0

    @Column(name = "name", unique = true, nullable = false)
    lateinit var name: String

    @Column(name = "carb", nullable = false)
    var carb: Float = 0.0f

    @Column(name = "protein", nullable = false)
    var protein: Float = 0.0f

    @Column(name = "fat", nullable = false)
    var fat: Float = 0.0f

    @Column(name = "k_cal", nullable = false)
    var kCal: Float = 0.0f

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    open lateinit var created: LocalDateTime

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    open lateinit var modified: LocalDateTime

}