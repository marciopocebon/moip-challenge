package br.com.ms.moipchallenge.exception

import org.springframework.validation.FieldError

class ErrorObject(
        val message: String,
        field: String? = null,
        val parameter: Any? = null
) {

    val field = field?.let { toSnakeCase(it) }

    private fun toSnakeCase(field: String) = field
            .replace("(.)(\\p{Upper})".toRegex(), "$1_$2")
            .toLowerCase()

    companion object {
        fun toErrorObject(error: FieldError) =
                ErrorObject(error.defaultMessage ?: "", error.field, error.rejectedValue ?: "")
    }

}