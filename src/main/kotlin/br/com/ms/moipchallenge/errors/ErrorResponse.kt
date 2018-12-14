package br.com.ms.moipchallenge.errors

class ErrorResponse(
        val message: String,
        val errors: List<ErrorObject>
)