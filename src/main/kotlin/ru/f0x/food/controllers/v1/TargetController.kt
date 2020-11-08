package ru.f0x.food.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.food.createSuccessResponseEntity
import ru.f0x.food.getUser
import ru.f0x.food.models.dto.ResponseDTO
import ru.f0x.food.models.dto.calculator.CalculationResult
import ru.f0x.food.services.calculator.ITargetCalculationService
import java.security.Principal

@RestController
@RequestMapping(value = ["/api/v1/target"])
class TargetController(private val service: ITargetCalculationService) {

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getTargetCalculationResult(principal: Principal): ResponseEntity<ResponseDTO<CalculationResult>> {
        return createSuccessResponseEntity(service.calculate(principal.getUser()))
    }
}