package br.com.ms.moipchallenge.repositories

import br.com.ms.moipchallenge.models.Payment
import org.springframework.data.repository.Repository

interface PaymentRepository : Repository<Payment, Long> {

    fun <T : Payment> save(payment: T): T

    fun findById(id: Long): Payment?

}