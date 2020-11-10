package ru.f0x.food.models.entity

import javax.persistence.*

@Entity
@Table(name = "food_events")
open class FoodEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "weight_gram", nullable = false)
    open var weightGram: Float = 0F

    @JoinTable(
            name = "food"
    )
    @OneToOne(fetch = FetchType.EAGER)
    open lateinit var food: FoodEntity

    @Column("event_id")
    open var eventId: Int = 0

}