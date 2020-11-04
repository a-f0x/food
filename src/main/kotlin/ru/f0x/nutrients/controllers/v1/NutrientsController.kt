package ru.f0x.nutrients.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getAllNutrients(): ResponseEntity<ResponseDTO<List<NutrientDTO>>> = createSuccessResponseEntity(service.getAll())

    @PutMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun updateNutrient(
            @RequestBody @Valid nutrientDTO: NutrientDTO): ResponseEntity<ResponseDTO<NutrientDTO>> =
            createSuccessResponseEntity(service.update(nutrientDTO))

    @DeleteMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun deleteNutrient(@RequestBody ids: List<Int>): ResponseEntity<ResponseDTO<String>> {
        service.delete(ids)
        return createSuccessResponseEntity("success")
    }

    @GetMapping(path = ["/search/name"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun searchNutrientByName(@RequestParam("query") query: String): ResponseEntity<ResponseDTO<List<NutrientDTO>>> =
            createSuccessResponseEntity(service.findByName(query))

    @GetMapping(path = ["/search/manufacturer"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun searchNutrientByManufacturer(@RequestParam("query") query: String): ResponseEntity<ResponseDTO<List<NutrientDTO>>> =
            createSuccessResponseEntity(service.findByManufacturer(query))

}