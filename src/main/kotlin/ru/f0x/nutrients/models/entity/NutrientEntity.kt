package ru.f0x.nutrients.models.entity

import javax.persistence.*

@Entity
@Table("nutrients")
class NutrientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column("name", unique = true, nullable = false)
    lateinit var name: String

    @Column("manufacturer", nullable = false)
    lateinit var manufacturer: String

    @Column("carbohydrates", nullable = false)
    var carbohydrates: Float = 0.0f

    @Column("proteins", nullable = false)
    var proteins: Float = 0.0f

    @Column("fats", nullable = false)
    var fats: Float = 0.0f

    @Column("kilocalories", nullable = false)
    var kilocalories: Float = 0.0f

}