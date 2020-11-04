package ru.f0x.nutrients.models.entity

import javax.persistence.*

@Entity
@Table(name = "nutrients")
class NutrientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "name", unique = true, nullable = false)
    lateinit var name: String

    @Column(name = "manufacturer", nullable = false)
    lateinit var manufacturer: String

    @Column(name = "carbohydrates", nullable = false)
    var carbohydrates: Float = 0.0f

    @Column(name = "proteins", nullable = false)
    var proteins: Float = 0.0f

    @Column(name = "fats", nullable = false)
    var fats: Float = 0.0f

    @Column(name = "kilocalories", nullable = false)
    var kilocalories: Float = 0.0f

}