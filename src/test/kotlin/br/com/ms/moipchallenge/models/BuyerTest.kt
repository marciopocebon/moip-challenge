package br.com.ms.moipchallenge.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import javax.validation.Validation.buildDefaultValidatorFactory

class BuyerTest {

    @Test
    fun givenValidFields_validationShouldReturnZeroErrors(){
        val buyer = Buyer("Buyer", "buyer@gmail.com", "48451057055")
        assertThat(validate(buyer).errorCount).isEqualTo(0)
    }

    @Test
    fun givenInvalidFields_validationShouldReturnNumberOfErrorsEqualFour(){
        val buyer = Buyer("", "", "")
        assertThat(validate(buyer).errorCount).isEqualTo(4)
    }

    private fun validate(buyer: Buyer) = BeanPropertyBindingResult(buyer, "buyer")
            .also { SpringValidatorAdapter(buildDefaultValidatorFactory().validator).validate(buyer, it) }
}