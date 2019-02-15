package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.enums.CardPaymentStatus.APPROVED
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Client
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate

class CardPaymentResourceTest {

    private lateinit var mapper: ObjectMapper

    @Before
    fun setup() {
        mapper = ObjectMapper()
        mapper.registerModule(SimpleModule().addSerializer(CardPayment::class.java, CardPaymentResource()))
    }

    @Test
    fun `given valid boleto payment should serialize it correctly`() {
        val client = Client(0)
        val buyer = Buyer("Buyer", "buyer@gmail.com", "65914918057")
        val card = Card("Holder", "5339456341711112", LocalDate.of(2030, 3, 14), "181")
        val cardPayment = CardPayment(BigDecimal(35.3), client, buyer, card, APPROVED)

        val json = format(mapper.writeValueAsString(cardPayment)).substringBefore(",\"_links")

        assertThat(json).isEqualTo(responseJson())
    }

    private fun format(json: String): String {
        val begin = json.substringBefore("expirationDate")
        val end = json.substringAfter("cvv")
        return "${begin}cvv$end"
    }

    private fun responseJson() = "{\"id\":0,\"amount\":35.30,\"paymentType\":\"CREDIT_CARD\",\"status\":\"APPROVED\",\"card\":{\"holderName\":\"Holder\",\"number\":\"5339456341711112\",\"cvv\":\"181\"},\"client\":{\"id\":0,\"name\":\"Client 0\"},\"buyer\":{\"id\":0,\"name\":\"Buyer\",\"email\":\"buyer@gmail.com\",\"cpf\":\"65914918057\"}"
}