package br.com.ms.moipchallenge.integration

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerIT {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    lateinit var buyer: Buyer

    @Before
    fun setup() {
        buyer = Buyer("Buyer", "buyer@gmail.com", "65914918057")
    }

    @Test
    fun `given valid boleto payment request should return created status`() {
        val boletoPaymentRequest = BoletoPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer)

        mvc.perform(post("/payments/boleto")
                .content(mapper.writeValueAsString(boletoPaymentRequest))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated)
    }

    @Test
    fun `given valid card payment request should return created status`() {
        val card = Card("Holder", "5339456341711112", LocalDate.now().plusDays(1), "181")
        val cardPaymentRequest = CardPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer, card)

        mvc.perform(post("/payments/card")
                .content(mapper.writeValueAsString(cardPaymentRequest))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated)
    }

    @Test
    fun `given id of non existent payment should return not found status`() {
        mvc.perform(get("/payments/1"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `given id of existent payment should return ok status`() {
        val boletoPaymentRequest = BoletoPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer)

        mvc.perform(post("/payments/boleto")
                .content(mapper.writeValueAsString(boletoPaymentRequest))
                .contentType(APPLICATION_JSON))

        mvc.perform(get("/payments/1"))
                .andExpect(status().isOk)
    }
}