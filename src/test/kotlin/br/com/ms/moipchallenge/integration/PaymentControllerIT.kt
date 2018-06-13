package br.com.ms.moipchallenge.integration

import br.com.ms.moipchallenge.enums.Status.APPROVED
import br.com.ms.moipchallenge.enums.Status.REFUSED
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus.*
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PaymentControllerIT {

    @Autowired
    private lateinit var rest: TestRestTemplate
    private val client = Client("Client")
    private val amount = BigDecimal.TEN
    private val buyer = Buyer("Buyer", "buyer@test.com", "38004622062")

    @Test
    fun save_givenValidCardRequest_shouldReturn201AndApprovedStatus() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(client, amount, buyer, card)
        val response = rest.postForEntity("/payments/card", request, Map::class.java)

        assertEquals(CREATED.value().toLong(), response.statusCodeValue.toLong())
        assertEquals(APPROVED.format, response.body!!["status"])
    }

    @Test
    fun save_givenInvalidCardRequest_shouldReturn201AndRefusedStatus() {
        val card = Card()
        val request = CardPaymentRequest(client, amount, buyer, card)
        val response = rest.postForEntity("/payments/card", request, Map::class.java)
        val errors = response.body!!["errors"] as List<*>

        assertEquals(CREATED.value().toLong(), response.statusCodeValue.toLong())
        assertEquals(REFUSED.format, response.body!!["status"])
        assertEquals(7, errors.size.toLong())
        assertThat(response.body!!["errors"]).isInstanceOf(List::class.java)
    }


    @Test
    fun save_givenValidRequest_shouldReturn201AndBoletoNumber() {
        val payment = BoletoPayment(client, amount, buyer)
        val response = rest.postForEntity("/payments/boleto", payment, Map::class.java)
        assertEquals(CREATED.value().toLong(), response.statusCodeValue.toLong())
        assertNotNull(response.body!!["number"])
    }

    @Test
    fun findOne_givenIdOfExistingPayment_shouldReturn200() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(client, amount, buyer, card)
        rest.postForEntity("/payments/card", request, Map::class.java)
        val get = rest.getForEntity("/payments/1", Map::class.java)
        assertEquals(OK.value().toLong(), get.statusCodeValue.toLong())
    }

    @Test
    fun findOne_givenIdOfNonExistingPayment_shouldReturn404() {
        val get = rest.getForEntity("/payments/1", Map::class.java)
        assertEquals(NOT_FOUND.value().toLong(), get.statusCodeValue.toLong())
    }
}