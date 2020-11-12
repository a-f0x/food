package ru.f0x.food.controllers.v1

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.f0x.food.createSuccessResponseEntity
import ru.f0x.food.getUser
import ru.f0x.food.models.dto.ResponseDTO
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventsResultDTO
import ru.f0x.food.models.dto.event.ProgressDTO
import ru.f0x.food.services.events.IEventService
import java.security.Principal
import java.time.LocalDate
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/events"])
class EventsController(private val service: IEventService) {

    @PostMapping(path = ["/food"], produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun addFoodEvent(principal: Principal, @RequestBody @Valid dto: CreateEventForFoodDTO): ResponseEntity<ResponseDTO<ProgressDTO>> {
        return createSuccessResponseEntity(
                service.addFoodEvent(principal.getUser().id, dto)
        )
    }

    @PostMapping(path = ["/workout"], produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun addWorkoutEvent(principal: Principal, @RequestBody @Valid dto: CreateEventForWorkoutDTO): ResponseEntity<ResponseDTO<ProgressDTO>> {
        return createSuccessResponseEntity(
                service.addWorkoutEvent(principal.getUser().id, dto)
        )
    }


    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getEventsBetweenDate(principal: Principal,
                             @RequestParam(name = "start")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             startDate: LocalDate,
                             @RequestParam(name = "end")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             endDate: LocalDate): ResponseEntity<ResponseDTO<EventsResultDTO>> {

        return createSuccessResponseEntity(
                service.getEventsBetweenDate(principal.getUser().id, startDate, endDate)
        )

    }
}