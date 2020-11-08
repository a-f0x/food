package ru.f0x.food.models.dto.calculator

import ru.f0x.food.models.entity.Carbohydrate
import ru.f0x.food.models.entity.Fat
import ru.f0x.food.models.entity.Protein

/**
 * Исходя из пропорции 3:2:5 получаем: 3 + 2 + 5 = 10 частей.
 * Суточную калорийность (1 600 ккал) делим на 10 частей, получается, что 160 ккал приходится на 1 часть.
 * Умножаем полученное количество на данные из пропорции:
 * для белков 160 ккал х 3 = 480 ккал;
 * для жиров 160 ккал х 2 = 320 ккал;
 * для углеводов 160 ккал х 5 = 800 ккал.
 * Высчитываем БЖУ в граммах:
 *
 * 480 ккал / 4 = 120 г (белков);
 * 320 ккал / 9 = 35,6 г (жиров);
 * 800 ккал / 4 = 200 г (углеводов).
 * */
class MaleNutrientsCalculator(private val basicMetabolismKCal: Float) {

    fun calculate(): NutrientsResult {
        val part = basicMetabolismKCal / 10f
        val protein = Nutrient(Protein, part * 3)
        val fat = Nutrient(Fat, part * 2)
        val carbohydrate = Nutrient(Carbohydrate, part * 5)

        return NutrientsResult(protein, fat, carbohydrate)
    }
}