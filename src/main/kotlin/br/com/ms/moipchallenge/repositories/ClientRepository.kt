package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Client
import org.springframework.data.repository.Repository

interface ClientRepository : Repository<Client, Long> {

    fun save(client: Client): Client

    fun findByName(name: String): Client?
}