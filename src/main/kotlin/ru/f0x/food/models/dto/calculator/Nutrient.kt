package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.NutrientType

class Nutrient<T : NutrientType> private constructor(private val type: T) {

    val name: String = type.name

    var weightGram: Float = 0f
        private set

    var kCal: Float = 0f
        private set

    var kCalPerGram: Int = 0
        private set

    constructor(t: T, kCal: Float) : this(t) {
        this.kCal = kCal
        this.weightGram = kCal / t.kCalPerGram
        this.kCalPerGram = t.kCalPerGram
    }

    override fun toString(): String {
        return "Nutrient(type=$type, weightGram=$weightGram, kCal=$kCal)"
    }

}