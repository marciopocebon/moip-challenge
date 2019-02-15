package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Client
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class BoletoPaymentResourceTest {

    private lateinit var mapper: ObjectMapper

    @Before
    fun setup() {
        mapper = ObjectMapper()
        mapper.registerModule(SimpleModule().addSerializer(BoletoPayment::class.java, BoletoPaymentResource()))
    }

    @Test
    fun `given valid boleto payment should serialize it correctly`() {
        val client = Client(0)
        val buyer = Buyer("Buyer", "buyer@gmail.com", "65914918057")
        val boletoPayment = BoletoPayment(BigDecimal(35.3), client, buyer)

        val json = "${mapper.writeValueAsString(boletoPayment).substringBefore(",\"_links")}}"

        assertThat(json).isEqualTo(responseJson(boletoPayment.number))
    }

    private fun responseJson(number: String) = "{\"id\":0,\"amount\":35.30,\"paymentType\":\"BOLETO\",\"number\":\"$number\",\"client\":{\"id\":0,\"name\":\"Client 0\"},\"buyer\":{\"id\":0,\"name\":\"Buyer\",\"email\":\"buyer@gmail.com\",\"cpf\":\"65914918057\"}}"
}