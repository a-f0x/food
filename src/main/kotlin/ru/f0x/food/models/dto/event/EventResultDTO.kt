package ru.f0x.food.models.dto.event

import java.time.LocalDateTime

data class EventResultDTO(
        val startTime: LocalDateTime,
        val endTime: LocalDateTime,
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