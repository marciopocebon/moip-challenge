package br.com.ms.moipchallenge.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse private constructor(
        val message: String,
        val errors: List<ErrorObject>
) {

    companion object {
        fun buildResponse(message: String, status: HttpStatus, errors: List<ErrorObject>) =
                ResponseEntity(ErrorResponse(message, errors), status)
    }
}