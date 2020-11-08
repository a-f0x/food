package ru.f0x.food.exceptions

class FoodProductNotFoundException(nutrientId: Int) : Exception() {
    val details: Map<String, Any> = mapOf("id" to nutrientId)
    override val message: String
        get() {
            return "Nutrient not found"
        }
}