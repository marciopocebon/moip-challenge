package br.com.ms.moipchallenge.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import java.time.LocalDate
import javax.validation.Validation

class CardTest {

    @Test
    fun givenValidFields_validationShouldReturnZeroErrors(){
       val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        assertThat(validate(card).errorCount).isEqualTo(0)
    }

    @Test
    fun givenInvalidFields_validationShouldReturnNumberOfErrorsEqualSix(){
        val card = Card("", "", LocalDate.now().minusYears(1), "")
        assertThat(validate(card).errorCount).isEqualTo(6)
    }

    private fun validate(card: Card) = card
            .let { BeanPropertyBindingResult(card, "card") }
            .also { SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().validator).validate(card, it) }
}