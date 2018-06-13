package br.com.ms.moipchallenge.controller

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.enums.Status.APPROVED
import br.com.ms.moipchallenge.error.ErrorObject
import br.com.ms.moipchallenge.models.*
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import br.com.ms.moipchallenge.services.PaymentService
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(SpringRunner::class)
@WebMvcTest(PaymentController::class)
class PaymentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var mapper: ObjectMapper
    @MockBean
    private lateinit var paymentService: PaymentService
    private val client = Client("Client")
    private val amount = BigDecimal.TEN
    private val buyer = Buyer("Buyer", "buyer@test.com", "38004622062")

    @Test
    fun save_givenValidRequestAndCard_shouldReturn201AndApprovedStatus() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(client, amount, buyer, card)
        val payment = CardPayment(client, amount, buyer, card).apply { status = APPROVED }
        val response =  payment to listOf<ErrorObject>()

        whenever(paymentService.createCardPayment(any())).thenReturn(response)

        val json = mapper.writeValueAsString(request)
        this.mockMvc.perform(post("/payments/card").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().`is`(201))
                .andExpect(jsonPath<String>("$.status", `is`<String>("Aprovado")))
    }

    @Test
    fun save_givenValidRequestAndInvalidCard_shouldReturn201AndRefusedStatus() {
        val card = Card()
        val request = CardPaymentRequest(client, amount, buyer, card)
        val error = ErrorObject("A", "B", "C")
        val errors = listOf(error, error, error, error, error, error, error)
        val response = CardPayment(client, amount, buyer, card) to errors

        whenever(paymentService.createCardPayment(any())).thenReturn(response)

        val json = mapper.writeValueAsString(request)

        this.mockMvc.perform(post("/payments/card").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().`is`(201))
                .andExpect(jsonPath<String>("$.status", `is`<String>("Recusado")))
                .andExpect(jsonPath<Collection<*>>("$.errors", hasSize(7)))
    }

    @Test
    fun save_givenValidRequest_shouldReturn201AndBoletoNumber() {
        val payment = BoletoPayment(client, amount, buyer)

        whenever(paymentService.createBoletoPayment(any())).thenReturn(payment)
        val json = mapper.writeValueAsString(payment)

        this.mockMvc.perform(post("/payments/boleto").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().`is`(201))
                .andExpect(jsonPath("$.number", `is`(payment.boleto.number)))
    }

    @Test
    fun findOne_givenIdOfExistingPayment_sholdReturnPayment() {
        val payment = BoletoPayment(client, amount, buyer)

        whenever(paymentService.getPayment(any())).thenReturn(payment)

        this.mockMvc.perform(get("/payments/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath<String>("$.amount", `is`<String>("R$ 10,00")))
    }
}