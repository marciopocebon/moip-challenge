package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Buyer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

interface BuyerRepository : Repository<Buyer, Long> {

    fun save(buyer: Buyer): Buyer

    fun findById(id: Long): Buyer?

    fun findAll(pageable: Pageable): Page<Buyer>
}