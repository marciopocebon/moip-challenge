package br.com.ms.moipchallenge.errors.exception

import br.com.ms.moipchallenge.errors.ErrorResponse
import org.springframework.http.ResponseEntity

abstract class MoipChallengeException(
        override val message: String,
        val field: String,
        val parameter: Any
) : RuntimeException() {

    abstract fun errorResponse(): ResponseEntity<ErrorResponse>
}