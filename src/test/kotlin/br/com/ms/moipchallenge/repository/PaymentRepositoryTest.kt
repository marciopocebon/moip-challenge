package br.com.ms.moipchallenge.repository

import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.repositories.PaymentRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal

@RunWith(SpringRunner::class)
@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private lateinit var repository: PaymentRepository

    @Test
    fun findById_givenIdOfExistingPayment_shouldReturnPayment() {
        val client = Client("Mário")
        val buyer = Buyer("Mário", "mario@gmail.com", "99759587025")
        val payment = repository.save(BoletoPayment(client, BigDecimal(30), buyer))
        val found = repository.find(payment.id)
        assertNotNull(found)
        assertEquals(payment.id, found?.id)
    }

    @Test
    fun findById_givenIdOfNonExistingPayment_shouldReturnNull() {
        val found = repository.find(10)
        assertNull(found)
    }
}