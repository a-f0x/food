package ru.f0x.nutrients

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import ru.f0x.nutrients.exceptions.NotAcceptableDataException
import ru.f0x.nutrients.exceptions.NutrientNotFoundException
import ru.f0x.nutrients.models.dto.ErrorDTO
import ru.f0x.nutrients.models.dto.ResponseDTO
import java.util.*

fun <T : Any> Optional<T>.getOrNull(): T? {
    return if (isPresent)
        get()
    else null
}

fun <T : Any> createSuccessResponseEntity(data: T?): ResponseEntity<ResponseDTO<T>> = ResponseEntity.ok(
        ResponseDTO(
                data = data,
                error = null
        )
)


fun NotAcceptableDataException.createValidationErrorResponseEntity(): ResponseEntity<ResponseDTO<Any>> = ResponseEntity(
        ResponseDTO(
                data = null,
                error = ErrorDTO(
                        code = ErrorDTO.ErrorCode.VALIDATION,
                        message = message,
                        details = details
                )
        ),
        HttpStatus.UNPROCESSABLE_ENTITY
)

fun NutrientNotFoundException.createNotFoundErrorResponseEntity(): ResponseEntity<ResponseDTO<Any>> = ResponseEntity(
        ResponseDTO(
                data = null,
                error = ErrorDTO(
                        code = ErrorDTO.ErrorCode.NOT_FOUND,
                        message = message,
                        details = details
                )
        ),
        HttpStatus.NOT_FOUND
)

fun HttpMessageNotReadableException.createValidationErrorResponseEntity(): ResponseEntity<Any> = ResponseEntity(
        ResponseDTO(
                data = null,
                error = ErrorDTO(
                        code = ErrorDTO.ErrorCode.VALIDATION,
                        message = message!!,
                        details = null
                )
        ),
        HttpStatus.UNPROCESSABLE_ENTITY
)

fun MissingServletRequestParameterException.createBadRequestErrorResponseEntity(): ResponseEntity<Any> = ResponseEntity(
        ResponseDTO<Any>(
                data = null,
                error = ErrorDTO(
                        code = ErrorDTO.ErrorCode.BAD_REQUEST,
                        message = message,
                        details = null
                )
        ),
        HttpStatus.BAD_REQUEST
)

fun createInternalServerErrorResponseEntity(errorMessage: String): ResponseEntity<Any> = ResponseEntity(
        ResponseDTO<Any>(
                data = null,
                error = ErrorDTO(
                        code = ErrorDTO.ErrorCode.SERVER,
                        message = errorMessage,
                        details = null
                )
        ),
        HttpStatus.INTERNAL_SERVER_ERROR
)


inline fun <T> Iterable<T>.containsByPredicate(predicate: (T) -> Boolean): Boolean {
    if (this is Collection && isEmpty()) return false
    for (element in this) if (predicate(element)) return true
    return false
}
