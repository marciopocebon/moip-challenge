package br.com.ms.moipchallenge.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BoletoFactoryTest {

    @Test
    fun shouldGenerateBoletoNumberWithSizeEqualsFortyFive(){
        val boletoNumber = BoletoFactory.generate()

        assertThat(boletoNumber).hasSize(45)
        assertThat(boletoNumber).containsOnlyDigits()
    }
}