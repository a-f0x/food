package ru.f0x.nutrients.services

import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class DateTimeService(private val clock: Clock) : IDateTimeService {

    companion object {
        const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        private val timeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
    }

    override fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now(clock)
    }

    override fun formatTime(time: LocalDateTime): String = timeFormatter.format(time)


    override fun getCurrentMills(): Long {
        return ZonedDateTime.now(clock).toInstant().toEpochMilli()
    }

    override fun getMillsFromDateTime(dateTime: LocalDateTime): Long {
        return dateTime.atZone(clock.zone).toEpochSecond()
    }
}