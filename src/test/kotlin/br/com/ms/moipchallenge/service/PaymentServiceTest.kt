package br.com.ms.moipchallenge.service

import br.com.ms.moipchallenge.enums.Status.APPROVED
import br.com.ms.moipchallenge.enums.Status.REFUSED
import br.com.ms.moipchallenge.exception.EntityNotFoundException
import br.com.ms.moipchallenge.models.*
import br.com.ms.moipchallenge.repositories.PaymentRepository
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import br.com.ms.moipchallenge.services.PaymentService
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
    var card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")

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
        whenever(paymentRepository.find(any())).thenReturn(null)

        paymentService.getPayment(10)
    }
}