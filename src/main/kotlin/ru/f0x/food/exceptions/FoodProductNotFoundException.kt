package ru.f0x.food.exceptions

class FoodProductNotFoundException(foodProductId: Int) : Exception() {
    val details: Map<String, Any> = mapOf("id" to foodProductId)
    override val message: String
        get() {
            return "Food product not found"
        }
}