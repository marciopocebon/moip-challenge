package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.errors.exception.EntityNotFoundException
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.repositories.BuyerRepository
import br.com.ms.moipchallenge.utils.Messenger.message
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BuyerService(
        private val buyerRepository: BuyerRepository
) {
    fun save(buyer: Buyer) = buyerRepository.save(buyer)

    fun findById(id: Long) = buyerRepository.findById(id)
            ?: throw EntityNotFoundException(message("buyer.not.found"), "id", id)

    fun findAll(pageable: Pageable) = buyerRepository.findAll(pageable)
}