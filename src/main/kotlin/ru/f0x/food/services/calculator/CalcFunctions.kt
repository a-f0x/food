package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.Nutrient
import ru.f0x.food.models.dto.calculator.NutrientsResult
import ru.f0x.food.models.dto.calculator.TargetCalculationResult
import ru.f0x.food.models.dto.event.EventSum
import ru.f0x.food.models.entity.*
import ru.f0x.food.models.entity.events.EventsReportRowEntity

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
fun calculateFemaleNutrientResult(basicMetabolismKCal: Float): NutrientsResult {
    val part = basicMetabolismKCal / 8.7f
    val protein = Nutrient.createFromKCal(Protein, part * 2.2f)
    val fat = Nutrient.createFromKCal(Fat, part * 2f)
    val carbohydrate = Nutrient.createFromKCal(Carbohydrate, part * 4.5f)

    return NutrientsResult(protein, fat, carbohydrate)
}

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
fun calculateMaleNutrientResult(basicMetabolismKCal: Float): NutrientsResult {
    val part = basicMetabolismKCal / 10f
    val protein = Nutrient.createFromKCal(Protein, part * 3)
    val fat = Nutrient.createFromKCal(Fat, part * 2)
    val carbohydrate = Nutrient.createFromKCal(Carbohydrate, part * 5)

    return NutrientsResult(protein, fat, carbohydrate)
}

fun UserProfileEntity.calculateTargetForUserProfile(): TargetCalculationResult {
    return TargetCalculator(
            weight,
            height,
            age,
            activity,
            sex
    ).calculate(target)
}

//fun List<EventEntity>.calculateEventSum(): EventSum {
//    var proteinAccumulator = 0F
//    var fatAccumulator = 0F
//    var carbAccumulator = 0F
//    var kCalAccumulator = 0F
//
//    forEach { event ->
//        proteinAccumulator += event.getProteinWeightGram()
//        fatAccumulator += event.getFatWeightGram()
//        carbAccumulator += event.getCarbWeightGram()
//        kCalAccumulator += if (event.type == EventTypeEnum.WORKOUT) -1 * event.kCal else event.kCal
//
//    }
//    return EventSum(
//            proteinAccumulator,
//            fatAccumulator,
//            carbAccumulator,
//            kCalAccumulator
//
//    )
//}


fun List<EventsReportRowEntity>.calculateEventSum(): EventSum {
    var proteinAccumulator = 0F
    var fatAccumulator = 0F
    var carbAccumulator = 0F
    var kCalAccumulator = 0F

    forEach { event ->
        proteinAccumulator += event.getProteinWeightGram()
        fatAccumulator += event.getFatWeightGram()
        carbAccumulator += event.getCarbWeightGram()
        kCalAccumulator += if (event.type == EventTypeEnum.WORKOUT) -1 * event.eventKCal else event.getFoodKCalFromWeightGram()

    }
    return EventSum(
            proteinAccumulator,
            fatAccumulator,
            carbAccumulator,
            kCalAccumulator

    )
}