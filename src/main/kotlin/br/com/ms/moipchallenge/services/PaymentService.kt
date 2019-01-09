package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.enums.CardPaymentStatus.APPROVED
import br.com.ms.moipchallenge.enums.CardPaymentStatus.DECLINED
import br.com.ms.moipchallenge.errors.ErrorObject
import br.com.ms.moipchallenge.errors.exception.EntityNotFoundException
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Payment
import br.com.ms.moipchallenge.repositories.PaymentRepository
import br.com.ms.moipchallenge.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import br.com.ms.moipchallenge.utils.Messenger.message
import br.com.ms.moipchallenge.utils.validator.CardValidator
import org.springframework.stereotype.Service

@Service
class PaymentService(
        private val paymentRepository: PaymentRepository,
        private val buyerService: BuyerService,
        private val clientService: ClientService
) {

    fun saveBoletoPayment(request: BoletoPaymentRequest): BoletoPayment {
        val client = clientService.save(request.clientId)
        val buyer = buyerService.save(request.buyer)

        return savePayment(BoletoPayment(request.amount, client, buyer)) as BoletoPayment
    }

    fun saveCardPayment(request: CardPaymentRequest): Pair<CardPayment, List<ErrorObject>?> {
        val client = clientService.save(request.clientId)
        val buyer = buyerService.save(request.buyer)
        val errors = CardValidator.validate(request.card)
        val status = if (errors != null) DECLINED else APPROVED

        return savePayment(CardPayment(request.amount, client, buyer, request.card, status)) as CardPayment to errors
    }

    fun findById(id: Long): Payment = paymentRepository.findById(id)
            ?: throw EntityNotFoundException(message("payment.not.found"), "id", id)

    private fun savePayment(payment: Payment) = paymentRepository.save(payment)
}