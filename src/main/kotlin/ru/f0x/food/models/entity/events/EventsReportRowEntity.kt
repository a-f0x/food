package ru.f0x.food.models.entity.events

import ru.f0x.food.models.entity.EventTypeEnum
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class EventsReportRowEntity {

    @Id
    @Column(name = "e_id")
    var id: Int = 0

    @Column(name = "grouped_by_hours")
    lateinit var groupedByHours: LocalDateTime

    @Column(name = "e_type")
    lateinit var type: EventTypeEnum

    @Column(name = "e_name")
    lateinit var eventName: String

    @Column(name = "e_kcal")
    var eventKCal: Float = 0f

    @Column(name = "e_utime")
    lateinit var userTime: LocalDateTime

    @Column(name = "food_weight_gram")
    var foodWeightGram: Float? = null

    @Column(name = "food_id")
    var foodId: Int? = null

    @Column(name = "food_name")
    var foodName: String? = null

    @Column(name = "protein")
    var protein: Float? = null

    @Column(name = "fat")
    var fat: Float? = null

    @Column(name = "carb")
    var carb: Float? = null

    @Column(name = "food_kcal")
    var foodKCal: Float? = null

    fun getProteinWeightGram(): Float {
        if (type == EventTypeEnum.WORKOUT)
            return 0f
        return foodWeightGram ?: 0 * (protein ?: 0f / 100)
    }

    fun getFatWeightGram(): Float {
        if (type == EventTypeEnum.WORKOUT)
            return 0f
        return foodWeightGram ?: 0 * (fat ?: 0f / 100)
    }

    fun getCarbWeightGram(): Float {
        if (type == EventTypeEnum.WORKOUT)
            return 0f
        return foodWeightGram ?: 0 * (carb ?: 0f / 100)

    }

    fun getFoodWeightGram(): Float {
        if (type == EventTypeEnum.WORKOUT)
            return 0f
        return foodWeightGram ?: 0f
    }

    fun getFoodKCalFromWeightGram(): Float {
        if (type == EventTypeEnum.WORKOUT)
            return 0f
        return foodWeightGram ?: 0 * (foodKCal ?: 0f / 100)

    }
}