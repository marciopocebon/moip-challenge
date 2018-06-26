package br.com.ms.moipchallenge.error

import br.com.ms.moipchallenge.error.ErrorResponse.Companion.buildResponse
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(LOWEST_PRECEDENCE)
@RestControllerAdvice
class LowPriorityErrorHandler {


    @ExceptionHandler(value = [(HttpMessageNotReadableException::class)])
    @Order(1)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException) = ex
            .run { ErrorObject("Required request body is missing.") }
            .let { buildResponse("Bad Request.", BAD_REQUEST, listOf(it)) }

}