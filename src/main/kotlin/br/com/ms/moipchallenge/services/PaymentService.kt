package br.com.ms.moipchallenge.services

import br.com.ms.moipchallenge.errors.exception.EntityNotFoundException
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Payment
import br.com.ms.moipchallenge.repositories.PaymentRepository
import br.com.ms.moipchallenge.utils.Messenger.message
import br.com.ms.moipchallenge.views.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.views.requests.CardPaymentRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PaymentService(private val paymentRepository: PaymentRepository,
                     private val buyerService: BuyerService,
                     private val clientService: ClientService) {

    fun saveBoletoPayment(boletoPaymentRequest: BoletoPaymentRequest): Payment {
        val client = clientService.findById(boletoPaymentRequest.clientId)
        val buyer = buyerService.findById(boletoPaymentRequest.buyerId)
        return paymentRepository.save(BoletoPayment(boletoPaymentRequest.amount, client, buyer))
    }

    fun saveCardPayment(request: CardPaymentRequest): Payment {
        val client = clientService.findById(request.clientId)
        val buyer = buyerService.findById(request.buyerId)
        return paymentRepository.save(CardPayment(request.amount, client, buyer, request.card))
    }

    fun findById(id: Long): Payment = paymentRepository.findById(id)
            ?: throw EntityNotFoundException(message("payment.not.found"), "id", id)

    fun findAll(pageable: Pageable) = paymentRepository.findAll(pageable)
}