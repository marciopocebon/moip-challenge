package br.com.ms.moipchallenge.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse private constructor(
        val message: String,
        val code: Int,
        val errors: List<ErrorObject>) {

    companion object {
        fun buildResponse(message: String, status: HttpStatus, errors: List<ErrorObject>) =
                ResponseEntity(ErrorResponse(message, status.value(), errors), status)
    }
}