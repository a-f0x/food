package ru.f0x.food.models.dto.event

import java.time.LocalDate

data class ProgressDTO(
        val onDate: LocalDate,
        val targetProteinWeightGram: Float,
        val currentProteinWeightGram: Float,
        val progressProteinPercent: Float,
        val targetFatWeightGram: Float,
        val currentFatWeightGram: Float,
        val progressFatPercent: Float,
        val targetCarbWeightGram: Float,
        val currentCarbWeightGram: Float,
        val progressCarbPercent: Float,
        val targetKCal: Float,
        val currentKCal: Float,
        val progressKCalPercent: Float
)