package ru.f0x.food.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.food.createSuccessResponseEntity
import ru.f0x.food.getUser
import ru.f0x.food.models.dto.ResponseDTO
import ru.f0x.food.models.dto.event.CreateEventForFoodDTO
import ru.f0x.food.models.dto.event.CreateEventForWorkoutDTO
import ru.f0x.food.models.dto.event.EventResultDTO
import ru.f0x.food.services.events.IEventService
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/events"])
class EventsController(private val service: IEventService) {

    @PostMapping(path = ["/food"], produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun addFoodEvent(principal: Principal, @RequestBody @Valid dto: CreateEventForFoodDTO): ResponseEntity<ResponseDTO<EventResultDTO>> {
        return createSuccessResponseEntity(service.addFoodEvent(principal.getUser().id, dto))
    }

    @PostMapping(path = ["/workout"], produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun addWorkoutEvent(principal: Principal, @RequestBody @Valid dto: CreateEventForWorkoutDTO): ResponseEntity<ResponseDTO<EventResultDTO>> {
        return createSuccessResponseEntity(service.addWorkoutEvent(principal.getUser().id, dto))
    }
}