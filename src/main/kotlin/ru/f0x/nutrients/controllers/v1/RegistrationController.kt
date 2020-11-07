package ru.f0x.nutrients.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.nutrients.createSuccessResponseEntity
import ru.f0x.nutrients.models.dto.ResponseDTO
import ru.f0x.nutrients.models.dto.users.CreateUserDTO
import ru.f0x.nutrients.models.dto.users.UserDTO
import ru.f0x.nutrients.services.users.IUserService
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/register"])
class RegistrationController(private val userService: IUserService) {
    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)],
            consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun registerUser(@RequestBody @Valid createUserDTO: CreateUserDTO): ResponseEntity<ResponseDTO<UserDTO>> {
        return createSuccessResponseEntity(userService.registerUser(createUserDTO))
    }

}