package ru.f0x.nutrients.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.nutrients.createSuccessResponseEntity
import ru.f0x.nutrients.models.dto.CreateNutrientDTO
import ru.f0x.nutrients.models.dto.NutrientDTO
import ru.f0x.nutrients.models.dto.ResponseDTO
import ru.f0x.nutrients.services.INutrientService
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/nutrients"])
class NutrientsController(private val service: INutrientService) {
    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun add(@RequestBody @Valid contact: CreateNutrientDTO): ResponseEntity<ResponseDTO<NutrientDTO>> =
            createSuccessResponseEntity(service.add(contact))


}