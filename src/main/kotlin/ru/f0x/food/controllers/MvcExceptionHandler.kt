package ru.f0x.food.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.f0x.food.createBadRequestErrorResponseEntity
import ru.f0x.food.createInternalServerErrorResponseEntity
import ru.f0x.food.createNotFoundErrorResponseEntity
import ru.f0x.food.createValidationErrorResponseEntity
import ru.f0x.food.exceptions.FoodProductNotFoundException
import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.models.dto.ResponseDTO
import java.lang.reflect.UndeclaredThrowableException

@ControllerAdvice
class MvcExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        private const val INTERNAL_SERVER_ERROR = "Internal server error"
        private const val UNKNOWN_ERROR = "Unknown error"
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.error("Validation error.", ex)
        return ex.createValidationErrorResponseEntity()
    }

    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.error("Parameters error.", ex)
        return ex.createBadRequestErrorResponseEntity()
    }

    @ExceptionHandler(NotAcceptableDataException::class)
    fun handleNotAcceptableDataException(ex: NotAcceptableDataException): ResponseEntity<ResponseDTO<Any>> {
        logger.error("Validation error.", ex)
        return ex.createValidationErrorResponseEntity()
    }

    @ExceptionHandler(FoodProductNotFoundException::class)
    fun handleNutrientNotFoundException(ex: FoodProductNotFoundException): ResponseEntity<ResponseDTO<Any>> {
        logger.error("Food not found.", ex)
        return ex.createNotFoundErrorResponseEntity()
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(t: Throwable): ResponseEntity<*> {
        if (t is UndeclaredThrowableException) {
            val ex = t.undeclaredThrowable
            if (ex is NotAcceptableDataException) {
                return handleNotAcceptableDataException(ex)
            }
        }

        logger.error(INTERNAL_SERVER_ERROR, t)
        return createInternalServerErrorResponseEntity("$INTERNAL_SERVER_ERROR. ${t.message ?: UNKNOWN_ERROR}")
    }

}