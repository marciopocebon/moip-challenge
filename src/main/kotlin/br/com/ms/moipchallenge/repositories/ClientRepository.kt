package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Client
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

interface ClientRepository : Repository<Client, Long> {

    fun save(client: Client): Client

    fun findById(id: Long): Client?

    fun findAll(pageable: Pageable): Page<Client>
}