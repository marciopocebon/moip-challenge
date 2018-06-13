package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Buyer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BuyerRepository : CrudRepository<Buyer, Long> {

    fun findByCpf(cpf: String): Buyer?
}