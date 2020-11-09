package ru.f0x.food.models.dto.food

import ru.f0x.food.models.dto.calculator.Nutrient
import ru.f0x.food.models.dto.calculator.NutrientsResult
import ru.f0x.food.models.entity.Carbohydrate
import ru.f0x.food.models.entity.Fat
import ru.f0x.food.models.entity.Protein

open class FoodProductDTO(
        open val id: Int?,
        val name: String,
        val manufacturer: String?,
        val carbohydrates: Float,
        val proteins: Float,
        val fats: Float,
        val kiloCal: Float
) {

    fun getNutrients(weightGram: Float): NutrientsResult {
        return NutrientsResult(
                protein = calcProtein(weightGram),
                fat = calcFat(weightGram),
                carb = calcCarb(weightGram)
        )
    }

    private fun calcProtein(weightGram: Float): Nutrient<Protein> {
        val perGram = proteins / 100
        return Nutrient.createFromWeightGram(Protein, weightGram * perGram)
    }


    private fun calcFat(weightGram: Float): Nutrient<Fat> {
        val perGram = fats / 100
        return Nutrient.createFromWeightGram(Fat, weightGram * perGram)

    }

    private fun calcCarb(weightGram: Float): Nutrient<Carbohydrate> {
        val perGram = carbohydrates / 100
        return Nutrient.createFromWeightGram(Carbohydrate, weightGram * perGram)

    }
}