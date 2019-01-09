package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.enums.CardPaymentStatus.APPROVED
import br.com.ms.moipchallenge.enums.CardPaymentStatus.DECLINED
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.repositories.PaymentRepository
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class PaymentServiceTest {

    @InjectMocks
    lateinit var paymentService: PaymentService

    @Mock
    lateinit var paymentRepository: PaymentRepository

    @Mock
    lateinit var buyerService: BuyerService

    @Mock
    lateinit var clientService: ClientService

    lateinit var buyer: Buyer

    lateinit var client: Client

    @Before
    fun setup(){
        buyer = Buyer("Buyer", "buyer@gmail.com", "65914918057")
        client = Client(0)
    }

    @Test
    fun givenCardPaymentRequestWithValidCardShouldSaveAndReturnItWithApproveStatusAndNullErrors(){
        val card = Card("Holder", "5339456341711112", LocalDate.now().plusDays(1), "181")
        val cardPaymentRequest = CardPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer, card)
        val cardPayment = CardPayment(cardPaymentRequest.amount, client, buyer, card, APPROVED)

        whenever(buyerService.save(any())).thenReturn(buyer)
        whenever(clientService.save(any())).thenReturn(client)
        whenever(paymentRepository.save(any())).thenReturn(cardPayment)

        val (payment, errors) = paymentService.saveCardPayment(cardPaymentRequest)

        assertThat(payment.status).isEqualTo(APPROVED)
        assertThat(errors).isNull()
    }

    @Test
    fun givenCardPaymentRequestWithInvalidCardShouldSaveAndReturnItWithDeclinedStatusAndErrors(){
        val card = Card("", "", LocalDate.now().minusDays(1), "")
        val cardPaymentRequest = CardPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer, card)
        val cardPayment = CardPayment(cardPaymentRequest.amount, client, buyer, card, DECLINED)

        whenever(buyerService.save(any())).thenReturn(buyer)
        whenever(clientService.save(any())).thenReturn(client)
        whenever(paymentRepository.save(any())).thenReturn(cardPayment)

        val (payment, errors) = paymentService.saveCardPayment(cardPaymentRequest)

        assertThat(payment.status).isEqualTo(DECLINED)
        assertThat(errors)
                .isNotNull
                .hasSize(6)
    }
}