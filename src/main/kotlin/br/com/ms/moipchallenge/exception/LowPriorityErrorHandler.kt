package br.com.ms.moipchallenge.exception

import br.com.ms.moipchallenge.exception.ErrorResponse.Companion.buildResponse
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(LOWEST_PRECEDENCE)
@RestControllerAdvice
class LowPriorityErrorHandler {

    @Order(1)
    @ExceptionHandler(value = [(HttpMessageNotReadableException::class)])
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException) =
            buildResponse("Bad Request.", BAD_REQUEST, listOf(ErrorObject("Required request body is missing.")))
}