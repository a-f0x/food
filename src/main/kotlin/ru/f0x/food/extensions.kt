package ru.f0x.food

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.web.bind.MissingServletRequestParameterException
import ru.f0x.food.exceptions.NotAcceptableDataException
import ru.f0x.food.exceptions.NutrientNotFoundException
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.models.dto.ErrorDTO
import ru.f0x.food.models.dto.ResponseDTO
import java.security.Principal
import java.util.*

fun <T : Any> Optional<T>.getOrNull(): T? {
    return if (isPresent)
        get()
    else null
}

fun Principal.getUser(): CustomUserDetails {
    val p = (this as? AbstractAuthenticationToken)?.principal as? CustomUserDetails
    return p ?: throw RuntimeException("invalid user in header $this")
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
