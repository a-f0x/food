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

                eventKCal = (objectArray[3] as BigDecimal).toFloat()
                userTime = (objectArray[4] as Timestamp).toLocalDateTime()
            }

            return@getResultsByFunctions if (type == EventTypeEnum.WORKOUT) e
            else e.apply {
                foodWeightGram = (objectArray[5] as BigDecimal).toFloat()
                foodId = objectArray[6] as Int
                foodName = objectArray[7] as String
                protein = (objectArray[8] as BigDecimal).toFloat()
                fat = (objectArray[9] as BigDecimal).toFloat()
                carb = (objectArray[10] as BigDecimal).toFloat()
                foodKCal = (objectArray[11] as BigDecimal).toFloat()
            }
        }
    }


}