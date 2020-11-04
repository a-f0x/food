package ru.f0x.nutrients.exceptions

class NutrientNotFoundException(nutrientId: Int) : Exception() {
    val details: Map<String, Any> = mapOf("id" to nutrientId)
    override val message: String
        get() {
            return "Nutrient not found"
        }
}