package br.com.ms.moipchallenge.repository

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.repositories.BuyerRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class BuyerRepositoryTest {

    @Autowired
    private lateinit var repository: BuyerRepository

    @Test
    fun findByCpf_givenValidCpf_shouldReturnBuyer() {
        val expectedValue = "38004622062"
        repository.save(Buyer("Mário", "mario@gmail.com", expectedValue))
        val found = repository.findByCpf(expectedValue)
        assertNotNull(found)
        assertEquals(expectedValue, found?.cpf)
    }

    @Test
    fun findByCpf_givenNonExistentCpf_shouldReturnNull() {
        val found = repository.findByCpf("38004622062")
        assertNull(found)
    }
}