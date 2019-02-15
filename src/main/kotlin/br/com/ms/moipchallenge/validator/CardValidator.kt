package br.com.ms.moipchallenge.validator

import br.com.ms.moipchallenge.errors.ErrorObject
import br.com.ms.moipchallenge.models.Card
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import javax.validation.Validation

object CardValidator {

    fun validate(card: Card) = getFieldErrors(card)
            .map { transformInErrorObject(it) }
            .takeIf { it.isNotEmpty() }

    private fun getFieldErrors(card: Card): List<FieldError> {
        val validator = Validation.buildDefaultValidatorFactory().validator
        val bindingResult = BeanPropertyBindingResult(card, "card")

        SpringValidatorAdapter(validator).validate(card, bindingResult)

        return bindingResult.fieldErrors
    }

    private fun transformInErrorObject(fieldError: FieldError) = ErrorObject(
            message = fieldError.defaultMessage ?: "",
            field = fieldError.field,
            parameter = fieldError.rejectedValue ?: ""
    )

}