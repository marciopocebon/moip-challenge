package br.com.ms.moipchallenge.payment

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import java.math.BigDecimal
import javax.validation.Validation

class PaymentTest {

    private val client = Client("Client")
    private val buyer = Buyer("Buyer", "buyer@gmail.com", "48451057055")

    @Test
    fun givenPaymentWithValidAmount_validationShouldReturnZeroErrors() {
        val payment = BoletoPayment(client, BigDecimal.TEN, buyer)

        Assertions.assertThat(validate(payment).errorCount).isEqualTo(0)
    }

    @Test
    fun givenPaymentWithNegativeAmount_validationShouldReturnOneError() {
        val payment = BoletoPayment(client, BigDecimal("-1000"), buyer)

        Assertions.assertThat(validate(payment).errorCount).isEqualTo(1)
    }

    @Test
    fun givenPaymentWithAmountGreaterThanSixDigits_validationShouldReturnOneError() {
        val payment = BoletoPayment(client, BigDecimal("9999999"), buyer)

        Assertions.assertThat(validate(payment).errorCount).isEqualTo(1)
    }

    @Test
    fun givenPaymentWithAmountNumberOfDecimalCasesGreaterThan2_validationShouldReturnOneError() {
        val payment = BoletoPayment(client, BigDecimal("100.323"), buyer)

        Assertions.assertThat(validate(payment).errorCount).isEqualTo(1)
    }

    private fun validate(payment: Payment) = payment
            .let { BeanPropertyBindingResult(payment, "payment") }
            .also { SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().validator).validate(payment, it) }
}