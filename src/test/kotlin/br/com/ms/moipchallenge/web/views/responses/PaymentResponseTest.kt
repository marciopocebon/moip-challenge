package br.com.ms.moipchallenge.web.views.responses

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.payment.Card
import br.com.ms.moipchallenge.payment.CardPayment
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate

class PaymentResponseTest {

    private val buyer = Buyer("Buyer", "buyer@gmail.com", "48451057055")
    private val client = Client("Client")

    @Test
    fun buildCardPayment_givenCardPayment_shouldReturnPaymentResponse(){
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val payment = CardPayment(client, BigDecimal.TEN, buyer, card)

        val paymentResponse = PaymentResponse.buildPaymentResponse(payment)

        assertThat(paymentResponse["amount"]).isEqualTo("R$ 10,00")
        assertThat(paymentResponse["type"]).isEqualTo("Credit card")

        val paymentMethod = paymentResponse["payment_method"] as HashMap<*, *>

        assertThat(paymentMethod["status"]).isEqualTo("Refused")
        assertThat(paymentMethod["holder_name"]).isEqualTo("Holder")
        assertThat(paymentMethod["number"]).isEqualTo("5295333658646342")
        assertThat(paymentMethod["expiration_date"]).isEqualTo("19/11/2018")
        assertThat(paymentMethod["cvv"]).isEqualTo("711")

        val buyer = paymentResponse["buyer"] as Buyer

        assertThat(buyer.id).isEqualTo(0)
        assertThat(buyer.name).isEqualTo("Buyer")
        assertThat(buyer.email).isEqualTo("buyer@gmail.com")
        assertThat(buyer.cpf).isEqualTo("48451057055")

        val client = paymentResponse["client"] as Client

        assertThat(client.id).isEqualTo(0)
        assertThat(client.name).isEqualTo("Client")
    }

    @Test
    fun buildBoletoPayment_givenBoletoPayment_shouldReturnPaymentResponse(){
        val payment = BoletoPayment(client, BigDecimal.TEN, buyer)

        val paymentResponse = PaymentResponse.buildPaymentResponse(payment)

        assertThat(paymentResponse["amount"]).isEqualTo("R$ 10,00")
        assertThat(paymentResponse["type"]).isEqualTo("Boleto")

        val paymentMethod = paymentResponse["payment_method"] as HashMap<*, *>

        assertThat(paymentMethod["number"]).isNotNull()
        assertThat(paymentMethod["number"] as String).hasSize(52)

        val buyer = paymentResponse["buyer"] as Buyer

        assertThat(buyer.id).isEqualTo(0)
        assertThat(buyer.name).isEqualTo("Buyer")
        assertThat(buyer.email).isEqualTo("buyer@gmail.com")
        assertThat(buyer.cpf).isEqualTo("48451057055")

        val client = paymentResponse["client"] as Client

        assertThat(client.id).isEqualTo(0)
        assertThat(client.name).isEqualTo("Client")
    }
}