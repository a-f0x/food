package ru.f0x.nutrients.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.nutrients.createSuccessResponseEntity
import ru.f0x.nutrients.getUser
import ru.f0x.nutrients.models.dto.ResponseDTO
import ru.f0x.nutrients.models.dto.calculator.TargetCalculationResultDTO
import ru.f0x.nutrients.services.calculator.ITargetCalculationService
import java.security.Principal

@RestController
@RequestMapping(value = ["/api/v1/target"])
class TargetController(private val service: ITargetCalculationService) {

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getTargetCalculationResult(principal: Principal): ResponseEntity<ResponseDTO<TargetCalculationResultDTO>> {
        return createSuccessResponseEntity(service.calculate(principal.getUser()))
    }
}