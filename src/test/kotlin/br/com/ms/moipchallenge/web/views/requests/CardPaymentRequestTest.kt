package br.com.ms.moipchallenge.web.views.requests

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.Card
import br.com.ms.moipchallenge.payment.CardPayment
import br.com.ms.moipchallenge.payment.Status
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate

class CardPaymentRequestTest {

    @Test
    fun toObject_givenCardPaymentRequest_returnCardPayment() {
        val buyer = Buyer("Buyer", "buyer@gmail.com", "48451057055")
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(Client("Client"), BigDecimal.TEN, buyer, card)

        val payment = request.toObject()

        assertThat(payment).isInstanceOf(CardPayment::class.java)
        assertThat(payment.status).isEqualTo(Status.REFUSED)
        assertThat(payment.card.holderName).isEqualTo("Holder")
        assertThat(payment.card.number).isEqualTo("5295333658646342")
        assertThat(payment.card.expirationDate).isEqualTo(LocalDate.of(2018, 11, 19))
        assertThat(payment.card.cvv).isEqualTo("711")

    }
}