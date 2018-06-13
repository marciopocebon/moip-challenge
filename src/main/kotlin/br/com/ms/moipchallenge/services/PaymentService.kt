package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.enums.Status.APPROVED
import br.com.ms.moipchallenge.exception.EntityNotFoundException
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Payment
import br.com.ms.moipchallenge.repositories.PaymentRepository
import br.com.ms.moipchallenge.validator.CardValidator
import org.springframework.stereotype.Service

@Service

class PaymentService(private val paymentRepository: PaymentRepository) {

    fun createBoletoPayment(payment: BoletoPayment) = paymentRepository.save(payment)

    fun createCardPayment(payment: CardPayment) = CardValidator.validate(payment.card)
            .also { if (it.isEmpty()) payment.status = APPROVED }
            .let { Pair(paymentRepository.save(payment), it) }

    fun getPayment(paymentId: Long): Payment = paymentRepository.find(paymentId)
            ?: throw EntityNotFoundException("No payment with requested ID was found.", "id", paymentId)
}