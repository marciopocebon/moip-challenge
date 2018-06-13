package br.com.ms.moipchallenge.error

import org.springframework.validation.FieldError

class ErrorObject(val message: String,
                  field: String,
                  val parameter: Any) {

    val field = field.replace("(.)(\\p{Upper})".toRegex(), "$1_$2").toLowerCase()

    companion object {
        fun toErrorObject(errors: List<FieldError>) = errors
                .map { ErrorObject(it.defaultMessage ?: "", it.field, it.rejectedValue ?: "") }
                .sortedBy { it.field }
    }

}