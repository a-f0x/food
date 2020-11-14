package ru.f0x.food.repository.events

import org.springframework.stereotype.Service
import ru.f0x.food.models.entity.EventTypeEnum
import ru.f0x.food.models.entity.events.EventsReportRowEntity
import ru.f0x.food.repository.AbstractQueryFilter
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.EntityManager

@Service
class EventReportRepository(
        entityManager: EntityManager
) : AbstractQueryFilter(entityManager), IEventReportRepository {

    override fun getEventsBetweenDate(userId: Int, start: LocalDate, end: LocalDate): List<EventsReportRowEntity> {


        return getResultsByFunctions(
                "get_events",
                mapOf(
                        "_user_id " to userId,
                        "_start_user_time" to start.atStartOfDay(),
                        "_end_user_time" to LocalDateTime.of(end, LocalTime.MAX)
                ),
                null
        ) {
            val objectArray = it as Array<Any>
            val type = EventTypeEnum.valueOf(objectArray[2] as String)
            val e = EventsReportRowEntity().apply {
                groupedByHours = (objectArray[0] as Timestamp).toLocalDateTime()
                id = objectArray[1] as Int
                this.type = type
                eventName = objectArray[3] as String
                eventKCal = (objectArray[4] as BigDecimal).toFloat()
                userTime = (objectArray[5] as Timestamp).toLocalDateTime()
            }

            return@getResultsByFunctions if (type == EventTypeEnum.WORKOUT) e
            else e.apply {
                foodWeightGram = (objectArray[6] as BigDecimal).toFloat()
                foodId = objectArray[7] as Int
                foodName = objectArray[8] as String
                protein = (objectArray[9] as BigDecimal).toFloat()
                fat = (objectArray[10] as BigDecimal).toFloat()
                carb = (objectArray[11] as BigDecimal).toFloat()
                foodKCal = (objectArray[12] as BigDecimal).toFloat()
            }
        }
    }


}