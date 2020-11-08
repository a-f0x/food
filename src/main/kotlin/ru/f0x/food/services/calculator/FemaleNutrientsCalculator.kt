package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.Nutrient
import ru.f0x.food.models.dto.calculator.NutrientsResult
import ru.f0x.food.models.entity.Carbohydrate
import ru.f0x.food.models.entity.Fat
import ru.f0x.food.models.entity.Protein

/**
 * Исходя из пропорции 2,2:2:4,5 получаем: 2,2 + 2 + 4,5 = 8,7 частей.
 * Суточную калорийность (1 300 ккал) делим на 8,7 частей, получается, что 149,4 ккал приходится на 1 часть.
 * Умножаем полученное количество на данные из пропорции:
 * для белков 149,4 ккал х 2,2 = 328,7 ккал;
 * для жиров 149,4 ккал х 2 = 298,8 ккал;
 * для углеводов 149,4 ккал х 4,5 = 672,3 ккал.
 * Высчитываем БЖУ в граммах:
 * 328,7 ккал / 4 = 82,2 г (белков);
 * 298,8 ккал / 9 = 33,2 г (жиров);
 * 672,3 ккал / 4 = 168,1 г (углеводов).
 * */
class FemaleNutrientsCalculator(private val basicMetabolismKCal: Float) {

    fun calculate(): NutrientsResult {
        val part = basicMetabolismKCal / 8.7f
        val protein = Nutrient(Protein, part * 2.2f)
        val fat = Nutrient(Fat, part * 2f)
        val carbohydrate = Nutrient(Carbohydrate, part * 4.5f)

        return NutrientsResult(protein, fat, carbohydrate)
    }
}