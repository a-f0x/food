package ru.f0x.nutrients.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.nutrients.createSuccessResponseEntity
import ru.f0x.nutrients.getUser
import ru.f0x.nutrients.models.dto.ResponseDTO
import ru.f0x.nutrients.models.dto.users.UserDTO
import ru.f0x.nutrients.services.users.IUserService
import java.security.Principal

@RestController
@RequestMapping(value = ["/api/v1/user"])
class UserController(private val userService: IUserService) {

    @GetMapping(path = ["/profile"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getProfile(principal: Principal): ResponseEntity<ResponseDTO<UserDTO>> {
        return createSuccessResponseEntity(userService.getUserProfile(principal.getUser()))
    }

}