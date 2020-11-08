package ru.f0x.food.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.f0x.food.createSuccessResponseEntity
import ru.f0x.food.getUser
import ru.f0x.food.models.dto.ResponseDTO
import ru.f0x.food.models.dto.food.CreateFoodProductDTO
import ru.f0x.food.models.dto.food.FoodProductDTO
import ru.f0x.food.services.food.IFoodProductsService
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/food"])
class FoodController(private val service: IFoodProductsService) {

    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun addFoodProduct(principal: Principal, @RequestBody @Valid food: CreateFoodProductDTO): ResponseEntity<ResponseDTO<FoodProductDTO>> =
            createSuccessResponseEntity(service.add(principal.getUser(), food))

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getAllFoodProducts(): ResponseEntity<ResponseDTO<List<FoodProductDTO>>> = createSuccessResponseEntity(service.getAll())

    @PutMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun updateFoodProduct(principal: Principal,
                          @RequestBody @Valid foodProductDTO: FoodProductDTO): ResponseEntity<ResponseDTO<FoodProductDTO>> =
            createSuccessResponseEntity(service.update(principal.getUser(), foodProductDTO))

    @DeleteMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun deleteFoodProduct(@RequestBody ids: List<Int>): ResponseEntity<ResponseDTO<String>> {
        service.delete(ids)
        return createSuccessResponseEntity("success")
    }

    @GetMapping(path = ["/search/name"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun searchFoodProductsByName(@RequestParam("query") query: String): ResponseEntity<ResponseDTO<List<FoodProductDTO>>> =
            createSuccessResponseEntity(service.findByName(query))

    @GetMapping(path = ["/search/manufacturer"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun searchFoodProducstByManufacturer(@RequestParam("query") query: String): ResponseEntity<ResponseDTO<List<FoodProductDTO>>> =
            createSuccessResponseEntity(service.findByManufacturer(query))

}