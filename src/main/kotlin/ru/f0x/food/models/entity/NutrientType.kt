package ru.f0x.food.models.entity

sealed class NutrientType(val kCalPerGram: Int) {
    val name = this.javaClass.simpleName
}

object Fat : NutrientType(9)

object Protein : NutrientType(4)

object Carbohydrate : NutrientType(4)