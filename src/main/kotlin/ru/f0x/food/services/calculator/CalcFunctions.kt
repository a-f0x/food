package ru.f0x.food.services.calculator

import ru.f0x.food.models.dto.calculator.TargetCalculationResult
import ru.f0x.food.models.dto.event.EventSum
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.UserProfileEntity
import ru.f0x.food.models.entity.events.EventsReportRowEntity

fun UserProfileEntity.calculateTargetForUserProfile(): TargetCalculationResult {
    return TargetCalculator(
            weight,
            height,
            age,
            sex,
            target,
            activity

    ).calculate()
}

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