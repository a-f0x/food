package ru.f0x.food.models.dto.food

open class FoodProductDTO(
        open val id: Int?,
        val name: String,
        val carb: Float,
        val protein: Float,
        val fat: Float,
        val kCal: Float
) {
//
//    fun getNutrients(weightGram: Float): NutrientsResult {
//        return NutrientsResult(
//                protein = calcProtein(weightGram),
//                fat = calcFat(weightGram),
//                carb = calcCarb(weightGram)
//        )
//    }
//
//    private fun calcProtein(weightGram: Float): Nutrient<Protein> {
//        val perGram = protein / 100
//        return Nutrient.createFromWeightGram(Protein, weightGram * perGram)
//    }
//
//
//    private fun calcFat(weightGram: Float): Nutrient<Fat> {
//        val perGram = fat / 100
//        return Nutrient.createFromWeightGram(Fat, weightGram * perGram)
//
//    }
//
//    private fun calcCarb(weightGram: Float): Nutrient<Carbohydrate> {
//        val perGram = carb / 100
//        return Nutrient.createFromWeightGram(Carbohydrate, weightGram * perGram)
//
//    }
}