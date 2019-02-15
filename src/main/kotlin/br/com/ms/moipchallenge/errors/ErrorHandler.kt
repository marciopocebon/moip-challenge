package br.com.ms.moipchallenge.errors

import br.com.ms.moipchallenge.errors.exception.MoipChallengeException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException) = ex
            .bindingResult
            .fieldErrors
            .map { ErrorObject(it.defaultMessage ?: "", it.field, it.rejectedValue ?: "") }
            .let { ResponseEntity(ErrorResponse("Invalid data", it), BAD_REQUEST) }

    @ExceptionHandler(MoipChallengeException::class)
    fun handleMoipChallengeException(ex: MoipChallengeException) = ex.errorResponse()
}
