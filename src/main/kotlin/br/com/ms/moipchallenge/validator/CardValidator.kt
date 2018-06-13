package br.com.ms.moipchallenge.validator

import br.com.ms.moipchallenge.error.ErrorObject
import br.com.ms.moipchallenge.models.Card
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import javax.validation.Validation
import javax.validation.Validator

class CardValidator {

    companion object {
        fun validate(card: Card) = getValidator()
                .let { getValidateResult(card, it) }
                .let { ErrorObject.toErrorObject(it.fieldErrors) }

        private fun getValidateResult(card: Card, validator: Validator): BeanPropertyBindingResult {
            val result = BeanPropertyBindingResult(card, "card")
            SpringValidatorAdapter(validator).apply { validate(card, result) }
            return result
        }

        private fun getValidator() = Validation.buildDefaultValidatorFactory().validator
    }

}