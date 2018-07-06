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

    fun createCardPayment(payment: CardPayment) = payment.card
            .let { CardValidator.validate(it) }
            .apply { if (isEmpty()) payment.status = APPROVED }
            .let { paymentRepository.save(payment) to it }

    fun getPayment(paymentId: Long): Payment = paymentRepository.findBy(paymentId)
            ?: throw EntityNotFoundException("No payment with requested ID was found", "id", paymentId)
}