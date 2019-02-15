package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.repositories.BuyerRepository
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
class BuyerServiceTest {

    @InjectMocks
    lateinit var buyerService: BuyerService

    @Mock
    lateinit var buyerRepository: BuyerRepository

    private lateinit var buyer: Buyer

    @Before
    fun setup(){
        buyer = Buyer("Buyer", "buyer@gmail.com", "65914918057")
    }
    @Test
    fun `given new buyer should save it`(){
        whenever(buyerRepository.findByEmail(anyString())).thenReturn(null)

        buyerService.save(buyer)

        verify(buyerRepository).save(any())
    }

    @Test
    fun `given an existent buyer should find and return it`(){
        whenever(buyerRepository.findByEmail(anyString())).thenReturn(buyer)

        buyerService.save(buyer)

        verify(buyerRepository, never()).save(any())
        verify(buyerRepository).findByEmail(anyString())
    }
}