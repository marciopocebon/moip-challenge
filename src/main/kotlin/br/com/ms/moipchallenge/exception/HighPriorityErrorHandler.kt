package br.com.ms.moipchallenge.exception

import br.com.ms.moipchallenge.exception.ErrorResponse.Companion.buildResponse
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.core.Ordered.HIGHEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
class HighPriorityErrorHandler {

    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException) = ex.bindingResult.fieldErrors
            .let { ErrorObject.toErrorObject(it) }
            .let { buildResponse("Invalid data", BAD_REQUEST, it) }

    @ExceptionHandler(value = [(EntityNotFoundException::class)])
    protected fun handleEntityNotFoundException(ex: EntityNotFoundException) = ex
            .run { ErrorObject(message, field, parameter) }
            .let { buildResponse("Entity not found", NOT_FOUND, listOf(it)) }

    @ExceptionHandler(value = [(MissingKotlinParameterException::class)])
    protected fun handleMissingKotlinParameterException(ex: MissingKotlinParameterException): ResponseEntity<ErrorResponse> {
        val responseMessage = "Mandatory parameters from ${ex.path[0].fieldName} weren't sent"
        val error = ErrorObject("Missing parameter", ex.path[1].fieldName)
        return buildResponse(responseMessage, BAD_REQUEST, listOf(error))
    }
}
