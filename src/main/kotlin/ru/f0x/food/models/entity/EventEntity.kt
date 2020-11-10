package ru.f0x.food.models.entity

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "events")
class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    /**
     * foreign_key на таблицу [UserEntity]
     * */
    @Column(name = "user_id", nullable = false)
    open var userId: Int = 0

    @Column(name = "name", nullable = false)
    open lateinit var name: String

    @Column(name = "k_cal", nullable = false)
    var kCal: Float = 0.0f

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", length = 64, nullable = false)
    lateinit var type: EventTypeEnum

    @JoinTable(
            name = "food_events",
            foreignKey = "event_id"
    )
    @OneToOne(fetch = FetchType.EAGER)
    open var foodEvent: FoodEventEntity? = null

    @Column(name = "is_removed")
    open var isRemoved: Boolean = false

    @Column(name = "created", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    open lateinit var created: LocalDateTime

    @Column(name = "user_time", columnDefinition = "TIMESTAMP", nullable = false)
    open lateinit var userTime: LocalDateTime


}