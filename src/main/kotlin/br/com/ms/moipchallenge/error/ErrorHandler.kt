package br.com.ms.moipchallenge.error

import br.com.ms.moipchallenge.exception.EntityNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders, status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        val errors = ErrorObject.toErrorObject(ex.bindingResult.fieldErrors)
        return buildResponse("Invalid data.", status, errors)
    }

    @ExceptionHandler(value = [(EntityNotFoundException::class)])
    protected fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<Any> {
        val error = ErrorObject(ex.message, ex.field, ex.parameter)
        return buildResponse("Entity not found.", HttpStatus.NOT_FOUND, listOf(error))
    }

    private fun buildResponse(message: String, status: HttpStatus, errors: List<ErrorObject>): ResponseEntity<Any> {
        return ResponseEntity(ErrorResponse(message, status.value(), errors), status)
    }
}