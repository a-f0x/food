package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.NutrientType

class Nutrient<T : NutrientType> private constructor(private val type: T) {

    companion object {
        fun <T : NutrientType> createFromKCal(t: T, kCal: Float): Nutrient<T> {
            return Nutrient(t).apply {
                this.kCal = kCal
                this.weightGram = kCal / t.kCalPerGram
                this.kCalPerGram = t.kCalPerGram
            }
        }

        fun <T : NutrientType> createFromWeightGram(t: T, weightGram: Float): Nutrient<T> {
            return Nutrient(t).apply {
                this.kCal = weightGram * t.kCalPerGram
                this.weightGram = weightGram
                this.kCalPerGram = t.kCalPerGram
            }
        }
    }

    val name: String = type.name

    var weightGram: Float = 0f
        private set

    var kCal: Float = 0f
        private set

    var kCalPerGram: Int = 0
        private set

    override fun toString(): String {
        return "Nutrient(type=$type, weightGram=$weightGram, kCal=$kCal)"
    }
}