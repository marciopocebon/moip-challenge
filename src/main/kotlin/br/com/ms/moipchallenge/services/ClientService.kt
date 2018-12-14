package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.errors.exception.EntityNotFoundException
import br.com.ms.moipchallenge.repositories.ClientRepository
import br.com.ms.moipchallenge.utils.Messenger.message
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ClientService(
        private val clientRepository: ClientRepository
) {
    fun save(client: Client) = clientRepository.save(client)

    fun findById(id: Long) = clientRepository.findById(id)
            ?: throw EntityNotFoundException(message("client.not.found"), "id", id)

    fun findAll(pageable: Pageable) = clientRepository.findAll(pageable)
}