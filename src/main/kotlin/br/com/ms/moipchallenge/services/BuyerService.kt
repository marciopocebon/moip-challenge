package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.repositories.BuyerRepository
import org.springframework.stereotype.Service

@Service
class BuyerService(
        private val buyerRepository: BuyerRepository
) {

    fun save(buyer: Buyer) = buyerRepository.findByEmail(buyer.email) ?: buyerRepository.save(buyer)
}