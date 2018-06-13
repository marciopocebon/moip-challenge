package br.com.ms.moipchallenge.error

class ErrorResponse(val message: String,
                    val code: Int,
                    val errors: List<ErrorObject>)