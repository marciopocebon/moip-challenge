package br.com.ms.moipchallenge.utils.validator

import br.com.ms.moipchallenge.errors.ErrorObject
import br.com.ms.moipchallenge.models.Card
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import javax.validation.Validation
import javax.validation.Validator

object CardValidator {

    fun validate(card: Card) = validate(card, Validation.buildDefaultValidatorFactory().validator)
            .fieldErrors
            .map { ErrorObject(it?.defaultMessage ?: "", it?.field ?: "", it?.rejectedValue ?: "") }
            .takeIf { it.isNotEmpty() }

    private fun validate(card: Card, validator: Validator) = BeanPropertyBindingResult(card, "card")
            .also { SpringValidatorAdapter(validator).validate(card, it) }

}