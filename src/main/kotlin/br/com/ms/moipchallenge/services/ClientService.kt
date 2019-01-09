package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.repositories.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
        private val clientRepository: ClientRepository
) {

    fun save(id: Long) = Client(id).let { clientRepository.findByName(it.name) ?: clientRepository.save(it) }
}