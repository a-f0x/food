package ru.f0x.food.models.dto

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseDTO<T : Any>(
        val data: T?,
        val error: ErrorDTO?
)

