package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.repositories.ClientRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClientServiceTest {

    @InjectMocks
    lateinit var clientService: ClientService

    @Mock
    lateinit var clientRepository: ClientRepository
    
    private lateinit var client: Client

    @Before
    fun setup(){
        client = Client(0)
    }
    @Test
    fun `given new client should save it`(){
        whenever(clientRepository.findByName(anyString())).thenReturn(null)

        clientService.save(client.id)

        verify(clientRepository).save(any())
    }

    @Test
    fun `given an existent buyer should find and return it`(){
        whenever(clientRepository.findByName(anyString())).thenReturn(client)

        clientService.save(client.id)

        verify(clientRepository, never()).save(any())
        verify(clientRepository).findByName(anyString())
    }
}