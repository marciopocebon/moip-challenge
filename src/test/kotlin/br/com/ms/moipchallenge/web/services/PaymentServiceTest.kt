package br.com.ms.moipchallenge.web.services

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.Status.APPROVED
import br.com.ms.moipchallenge.payment.Status.REFUSED
import br.com.ms.moipchallenge.exception.EntityNotFoundException
import br.com.ms.moipchallenge.payment.Card
import br.com.ms.moipchallenge.payment.CardPayment
import br.com.ms.moipchallenge.payment.Payment
import br.com.ms.moipchallenge.payment.PaymentRepository
import br.com.ms.moipchallenge.web.views.requests.CardPaymentRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class PaymentServiceTest {

    @InjectMocks
    private lateinit var paymentService: PaymentService
    @Mock
    private lateinit var paymentRepository: PaymentRepository

    private val client = Client("Client")
    private val amount = BigDecimal.TEN
    private val buyer = Buyer("Buyer", "buyer@test.com", "38004622062")
    private var card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")

    @Test
    fun createCardPayment_givenValidCreditCard_shouldReturnApprovedCardPayment() {
        val request = CardPaymentRequest(client, amount, buyer, card)
        val payment = CardPayment(client, amount, buyer, card).apply { status = APPROVED }
        whenever(paymentRepository.save(Mockito.any(Payment::class.java))).thenReturn(payment)

        val (cardPayment, errors) = paymentService.createCardPayment(request.toObject())

        assertThat(cardPayment.status).isEqualTo(APPROVED)
        assertThat(errors.size).isEqualTo(0)
    }

    @Test
    fun createCardPayment_givenInvalidCreditCard_shouldReturnRefusedCardPayment() {
        card = Card()
        val request = CardPaymentRequest(client, amount, buyer, card)
        val payment = CardPayment(client, amount, buyer, card)
        whenever(paymentRepository.save(Mockito.any(Payment::class.java))).thenReturn(payment)

        val (cardPayment, errors) = paymentService.createCardPayment(request.toObject())

        assertThat(cardPayment.status).isEqualTo(REFUSED)
        assertThat(errors.size).isEqualTo(7)
    }

    @Test(expected = EntityNotFoundException::class)
    fun getPayment_givenNonExistingPayment_shouldReturnEntityNotFoundException() {
        whenever(paymentRepository.findBy(any())).thenReturn(null)

        paymentService.getPayment(10)
    }
}