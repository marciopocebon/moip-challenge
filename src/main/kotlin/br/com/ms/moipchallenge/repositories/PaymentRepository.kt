package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Payment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

interface PaymentRepository : Repository<Payment, Long> {

    fun save(payment: Payment): Payment

    fun findById(id: Long) : Payment?

    fun findAll(pageable: Pageable): Page<Payment>
}