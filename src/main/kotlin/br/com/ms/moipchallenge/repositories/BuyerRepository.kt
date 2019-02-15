package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Buyer
import org.springframework.data.repository.Repository

interface BuyerRepository : Repository<Buyer, Long> {

    fun save(buyer: Buyer): Buyer

    fun findByEmail(email: String): Buyer?

}