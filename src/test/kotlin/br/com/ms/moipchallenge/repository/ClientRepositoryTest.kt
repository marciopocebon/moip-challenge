package br.com.ms.moipchallenge.repository

import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.repositories.ClientRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private lateinit var repository: ClientRepository

    @Test
    fun findById_givenIdOfExistingClient_shouldReturnClient() {
        repository.save(Client("Mário"))
        val expectedId = 1L
        val found = repository.find(expectedId)
        assertNotNull(found)
        assertEquals(expectedId, found?.id)
    }

    @Test
    fun findById_givenIdOfNonExistingClient_shouldReturnNull() {
        val found = repository.find(1L)
        assertNull(found)
    }
}