package br.com.ms.moipchallenge.payment

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : CrudRepository<Payment, Long>{

    @Query("SELECT p FROM Payment p WHERE p.id = ?1")
    fun findBy(id: Long) : Payment?
}