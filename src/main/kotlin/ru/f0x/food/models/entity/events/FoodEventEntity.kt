package ru.f0x.food.models.entity.events

import ru.f0x.food.models.entity.FoodEntity
import javax.persistence.*

@Entity
@Table(name = "food_events")
open class FoodEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "weight_gram", nullable = false)
    open var weightGram: Float = 0F

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id", referencedColumnName = "id", nullable = false)
    open lateinit var food: FoodEntity

}