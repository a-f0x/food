package ru.f0x.food.models.entity

sealed class NutrientType(val kCalPerGram: Int)

object Fat : NutrientType(9)

object Protein : NutrientType(4)

object Carbohydrate : NutrientType(4)