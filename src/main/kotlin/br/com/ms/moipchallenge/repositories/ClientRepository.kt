package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Client
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : CrudRepository<Client,Long>{

    @Query("SELECT c FROM Client c WHERE c.id = ?1")
    fun find(clientId: Long): Client?
}