package br.com.ms.moipchallenge.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import javax.validation.Validation

class ClientTest {

    @Test
    fun givenValidFields_validationShouldReturnZeroErrors(){
        val client = Client("Client")
        assertThat(validate(client).errorCount).isEqualTo(0)
    }

    @Test
    fun givenInvalidFields_validationShouldReturnNumberOfErrorsEqualOne(){
        val client = Client("")
        assertThat(validate(client).errorCount).isEqualTo(1)
    }

    private fun validate(client: Client) = BeanPropertyBindingResult(client, "client")
            .also { SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().validator).validate(client, it) }
}