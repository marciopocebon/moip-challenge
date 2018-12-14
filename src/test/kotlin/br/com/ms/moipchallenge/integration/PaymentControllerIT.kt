package br.com.ms.moipchallenge.integration

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.repositories.BuyerRepository
import br.com.ms.moipchallenge.repositories.ClientRepository
import br.com.ms.moipchallenge.repositories.PaymentRepository
import br.com.ms.moipchallenge.utils.Messenger
import br.com.ms.moipchallenge.views.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.views.requests.CardPaymentRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.env.Environment
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal
import java.time.LocalDate


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class PaymentControllerIT {

    @Autowired
    private lateinit var mapper: ObjectMapper
    @Autowired
    private lateinit var env: Environment
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    @Autowired
    private lateinit var clientRepository: ClientRepository
    @Autowired
    private lateinit var buyerRepository: BuyerRepository
    @Autowired
    private lateinit var paymentRepository: PaymentRepository
    private lateinit var mvc: MockMvc
    private lateinit var client: Client
    private lateinit var buyer: Buyer
    private val amount = BigDecimal.TEN

    @Before
    fun setup() {
        Messenger.env = env
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        client = clientRepository.save(Client("Client"))
        buyer = buyerRepository.save(Buyer("Buyer", "buyer@test.com", "38004622062"))
    }

    @Test
    fun save_givenValidCardRequest_shouldReturn201AndApprovedStatus() {
        val card = Card("Holder", "5295333658646342", LocalDate.of(2018, 11, 19), "711")
        val request = CardPaymentRequest(amount, client.id, buyer.id, card)

        mvc.perform(post("/payments/card")
                .content(mapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated)
    }

    @Test
    fun save_givenValidBoletoPayment_shouldReturn201AndBoletoNumber() {
        val request = BoletoPaymentRequest(amount, client.id, buyer.id)

        mvc.perform(post("/payments/boleto")
                .content(mapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated)
    }

    @Test
    fun findOne_givenIdOfNonExistingPayment_shouldReturn404() {
        mvc.perform(get("/payments/1"))
                .andExpect(status().isNotFound)
    }
}