package br.com.ms.moipchallenge.errors.exception

import br.com.ms.moipchallenge.errors.ErrorObject
import br.com.ms.moipchallenge.errors.ErrorResponse
import br.com.ms.moipchallenge.NOT_FOUND_GENERAL
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity

class EntityNotFoundException(
        message: String,
        field: String,
        parameter: Any
) : MoipChallengeException(message, field, parameter) {

    override fun errorResponse() = ResponseEntity(
            ErrorResponse(NOT_FOUND_GENERAL, listOf(ErrorObject(message, field, parameter))), NOT_FOUND)
}