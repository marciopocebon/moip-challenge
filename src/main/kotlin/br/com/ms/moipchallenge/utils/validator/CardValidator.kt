package br.com.ms.moipchallenge.utils.validator

import br.com.ms.moipchallenge.exception.ErrorObject.Companion.toErrorObject
import br.com.ms.moipchallenge.payment.Card
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import javax.validation.Validation
import javax.validation.Validator

class CardValidator {

    companion object {
        fun validate(card: Card) = validate(card, getValidator()).fieldErrors.map { toErrorObject(it) }

        private fun validate(card: Card, validator: Validator) =
                BeanPropertyBindingResult(card, "card").also { SpringValidatorAdapter(validator).validate(card, it) }

        private fun getValidator() = Validation.buildDefaultValidatorFactory().validator
    }

}