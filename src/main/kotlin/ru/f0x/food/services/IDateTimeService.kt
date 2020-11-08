package ru.f0x.food.services

import java.time.LocalDateTime

interface IDateTimeService {

    fun getCurrentTime(): LocalDateTime

    fun formatTime(time: LocalDateTime): String

    fun getCurrentMills(): Long

    fun getMillsFromDateTime(dateTime: LocalDateTime): Long
}

