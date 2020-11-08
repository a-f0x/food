package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.NutrientType

class Nutrient<T : NutrientType> private constructor(val type: T) {
    val name = type.name

    var weightGram: Float = 0f
        private set

    var kCal: Float = 0f
        private set

    constructor(t: T, kCal: Float) : this(t) {
        this.kCal = kCal
        this.weightGram = kCal / t.kCalPerGram
    }

    override fun toString(): String {
        return "Nutrient(type=$type, weightGram=$weightGram, kCal=$kCal)"
    }

}