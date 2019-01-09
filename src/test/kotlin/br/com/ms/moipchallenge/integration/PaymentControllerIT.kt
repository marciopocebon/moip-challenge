package br.com.ms.moipchallenge.integration

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import br.com.ms.moipchallenge.utils.Messenger
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
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

    @Autowired
    lateinit var environment: Environment

    lateinit var buyer: Buyer

    @Before
    fun setup() {
        buyer = Buyer("Buyer", "buyer@gmail.com", "65914918057")
    }

    @Test
    fun givenValidBoletoPaymentRequestShouldReturnCreatedStatus() {
        val boletoPaymentRequest = BoletoPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer)

        mvc.perform(post("/payments/boleto")
                .content(mapper.writeValueAsString(boletoPaymentRequest))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated)
    }

    @Test
    fun givenValidCardPaymentRequestShouldReturnCreatedStatus() {
        val card = Card("Holder", "5339456341711112", LocalDate.now().plusDays(1), "181")
        val cardPaymentRequest = CardPaymentRequest(BigDecimal.valueOf(35.3), 1, buyer, card)

        mvc.perform(post("/payments/card")
                .content(mapper.writeValueAsString(cardPaymentRequest))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated)
    }

    @Test
    fun givenIdOfNonExistentPaymentShouldReturnNotFoundStatus() {
        Messenger.env = environment

        mvc.perform(get("/payments/1"))
                .andExpect(status().isNotFound)
    }
}