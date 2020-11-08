package ru.f0x.food.controllers.v1

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0x.food.createSuccessResponseEntity
import ru.f0x.food.getUser
import ru.f0x.food.models.dto.ResponseDTO
import ru.f0x.food.models.dto.users.ProfileResponse
import ru.f0x.food.services.users.IUserService
import java.security.Principal

@RestController
@RequestMapping(value = ["/api/v1/user"])
class UserController(private val userService: IUserService) {

    @GetMapping(path = ["/profile"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getProfile(principal: Principal): ResponseEntity<ResponseDTO<ProfileResponse>> {
        return createSuccessResponseEntity(userService.getUserProfile(principal.getUser()))
    }

}