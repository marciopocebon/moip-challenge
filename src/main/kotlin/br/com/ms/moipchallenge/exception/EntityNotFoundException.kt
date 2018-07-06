package br.com.ms.moipchallenge.exception

class EntityNotFoundException(
        override val message: String,
        val field: String,
        val parameter: Any
) : RuntimeException()