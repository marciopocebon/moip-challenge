package br.com.ms.moipchallenge.web.views.responses

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.payment.Card
import br.com.ms.moipchallenge.payment.CardPayment
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate

class PaymentCreatedResponseTest {

    private val buyer = Buyer("Buyer", "buyer@gmail.com", "48451057055")
    private val client = Client("Client")

    @Test
    fun boletoPaymentCreated_givenBoletoPayment_shouldReturnBoletoNumber() {
        val payment = BoletoPayment(client, BigDecimal.TEN, buyer)

        val paymentCreated = CreatedPaymentResponse.boletoPaymentCreated(payment)

        assertThat(paymentCreated["number"] as String).hasSize(52)
    }

    @Test
    fun cardPaymentCreated_givenCardPayment_shouldReturnPaymentStatusEqualsRefused() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val payment = CardPayment(client, BigDecimal.TEN, buyer, card)

        val paymentCreated = CreatedPaymentResponse.cardPaymentCreated(payment, listOf())

        assertThat(paymentCreated["status"] as String).isEqualTo("Refused")
    }
}