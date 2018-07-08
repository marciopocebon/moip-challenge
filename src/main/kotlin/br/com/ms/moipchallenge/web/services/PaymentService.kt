package br.com.ms.moipchallenge.web.services

import br.com.ms.moipchallenge.payment.Status.APPROVED
import br.com.ms.moipchallenge.exception.EntityNotFoundException
import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.payment.CardPayment
import br.com.ms.moipchallenge.payment.Payment
import br.com.ms.moipchallenge.payment.PaymentRepository
import br.com.ms.moipchallenge.utils.validator.CardValidator
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