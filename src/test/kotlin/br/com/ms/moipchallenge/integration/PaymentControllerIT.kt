package br.com.ms.moipchallenge.integration

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.payment.Card
import br.com.ms.moipchallenge.payment.Status.APPROVED
import br.com.ms.moipchallenge.payment.Status.REFUSED
import br.com.ms.moipchallenge.web.views.requests.CardPaymentRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.*
import org.springframework.http.MediaType.APPLICATION_JSON
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
    @Autowired
    private lateinit var mapper: ObjectMapper
    private val client = Client("Client")
    private val amount = BigDecimal.TEN
    private val buyer = Buyer("Buyer", "buyer@test.com", "38004622062")
    private val headers = HttpHeaders().apply { contentType = APPLICATION_JSON }

    @Test
    fun save_givenValidCardRequest_shouldReturn201AndApprovedStatus() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(client, amount, buyer, card)
        val entity = HttpEntity(mapper.writeValueAsString(request), headers)

        val response = rest.postForEntity("/payments/card", entity, Map::class.java)

        assertEquals(CREATED.value().toLong(), response.statusCodeValue.toLong())
        assertEquals(APPROVED.format, response.body!!["status"])

        val arrayOfLinks = response.body!!["links"] as ArrayList<*>
        val links = arrayOfLinks[0] as LinkedHashMap<*, *>

        assertThat(links["rel"]).isEqualTo("self")
        assertThat(links["href"] as String).contains("/payments/1")
    }

    @Test
    fun save_givenInvalidCardRequest_shouldReturn201AndRefusedStatus() {
        val card = Card()
        val request = CardPaymentRequest(client, amount, buyer, card)
        val entity = HttpEntity(mapper.writeValueAsString(request), headers)

        val response = rest.postForEntity("/payments/card", entity, Map::class.java)
        val errors = response.body!!["errors"] as List<*>

        assertEquals(CREATED.value().toLong(), response.statusCodeValue.toLong())
        assertEquals(REFUSED.format, response.body!!["status"])
        assertEquals(7, errors.size.toLong())
    }


    @Test
    fun save_givenValidBoletoPayment_shouldReturn201AndBoletoNumber() {
        val payment = BoletoPayment(client, amount, buyer)
        val entity = HttpEntity(mapper.writeValueAsString(payment), headers)

        val response = rest.postForEntity("/payments/boleto", entity, Map::class.java)

        assertEquals(CREATED.value().toLong(), response.statusCodeValue.toLong())
        assertNotNull(response.body!!["number"])
        assertThat(response.body!!["number"] as String).hasSize(52)

    }

    @Test
    fun findOne_givenIdOfExistingCardPayment_shouldReturn200() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(client, amount, buyer, card)
        val entity = HttpEntity(mapper.writeValueAsString(request), headers)
        rest.postForEntity("/payments/card", entity, Map::class.java)

        val response = rest.getForEntity("/payments/1", Map::class.java)

        assertEquals(OK.value().toLong(), response.statusCodeValue.toLong())
        assertThat(response.body!!["amount"]).isEqualTo("R$ 10,00")
        assertThat(response.body!!["type"]).isEqualTo("Credit card")

        val paymentMethod = response.body!!["payment_method"] as HashMap<*, *>

        assertThat(paymentMethod["id"]).isEqualTo(1)
        assertThat(paymentMethod["status"]).isEqualTo("Approved")
        assertThat(paymentMethod["holder_name"]).isEqualTo("Holder")
        assertThat(paymentMethod["number"]).isEqualTo("5295333658646342")
        assertThat(paymentMethod["expiration_date"]).isEqualTo("19/11/2018")
        assertThat(paymentMethod["cvv"]).isEqualTo("711")

        val buyer = response.body!!["buyer"] as HashMap<*, *>
        assertThat(buyer["id"]).isEqualTo(1)
        assertThat(buyer["name"]).isEqualTo("Buyer")
        assertThat(buyer["email"]).isEqualTo("buyer@test.com")
        assertThat(buyer["cpf"]).isEqualTo("380.046.220-62")

        val client = response.body!!["client"] as HashMap<*, *>

        assertThat(client["id"]).isEqualTo(1)
        assertThat(client["name"]).isEqualTo("Client")

        val arrayOfLinks = response.body!!["links"] as ArrayList<*>
        val links = arrayOfLinks[0] as LinkedHashMap<*, *>

        assertThat(links["rel"]).isEqualTo("self")
        assertThat(links["href"] as String).contains("/payments/1")

    }

    @Test
    fun findOne_givenIdOfExistingBoletoPayment_shouldReturn200() {
        val request = BoletoPayment(client, amount, buyer)
        val entity = HttpEntity(mapper.writeValueAsString(request), headers)
        rest.postForEntity("/payments/boleto", entity, Map::class.java)

        val response = rest.getForEntity("/payments/1", Map::class.java)

        assertEquals(OK.value().toLong(), response.statusCodeValue.toLong())
        assertThat(response.body!!["type"]).isEqualTo("Boleto")

        val paymentMethod = response.body!!["payment_method"] as HashMap<*, *>

        assertThat(paymentMethod["id"]).isEqualTo(1)
        assertThat(paymentMethod["number"] as String).hasSize(52)
    }

    @Test
    fun findOne_givenIdOfNonExistingPayment_shouldReturn404() {
        val response = rest.getForEntity("/payments/1", Map::class.java)

        assertEquals(NOT_FOUND.value().toLong(), response.statusCodeValue.toLong())
        assertThat(response.body!!["message"]).isEqualTo("Entity not found")
        assertThat(response.body!!["code"]).isEqualTo(404)

        val arrayOfErrors = response.body!!["errors"] as ArrayList<*>
        val errors = arrayOfErrors[0] as LinkedHashMap<*, *>

        assertThat(errors["message"]).isEqualTo("No payment with requested ID was found")
        assertThat(errors["field"]).isEqualTo("id")
        assertThat(errors["parameter"]).isEqualTo(1)

    }
}