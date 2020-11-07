package ru.f0x.nutrients.models.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "nutrients")
class NutrientEntity {
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

    @Column(name = "manufacturer", nullable = false)
    var manufacturer: String = "Unknown"

    @Column(name = "carbohydrates", nullable = false)
    var carbohydrates: Float = 0.0f

    @Column(name = "proteins", nullable = false)
    var proteins: Float = 0.0f

    @Column(name = "fats", nullable = false)
    var fats: Float = 0.0f

    @Column(name = "kCal", nullable = false)
    var kCal: Float = 0.0f

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    open lateinit var created: LocalDateTime

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    open lateinit var modified: LocalDateTime

}