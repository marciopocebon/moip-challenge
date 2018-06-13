package br.com.ms.moipchallenge.controllers

import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import br.com.ms.moipchallenge.responses.CreatedPaymentResponse
import br.com.ms.moipchallenge.responses.PaymentResponse
import br.com.ms.moipchallenge.services.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("/boleto")
    fun createBoletoPayment(@Valid @RequestBody boletoPayment: BoletoPayment): ResponseEntity<Map<String, Any>> {
        val payment = paymentService.createBoletoPayment(boletoPayment)
        return ResponseEntity(CreatedPaymentResponse.boletoPaymentCreated(payment), HttpStatus.CREATED)
    }

    @PostMapping("/card")
    fun createCardPayment(@Valid @RequestBody request: CardPaymentRequest): ResponseEntity<Map<String, Any>> {
        val (payment, errors) = paymentService.createCardPayment(request.toObject())
        return ResponseEntity(CreatedPaymentResponse.cardPaymentCreated(payment, errors), HttpStatus.CREATED)
    }

    @GetMapping("/{paymentId}")
    fun getPayment(@PathVariable paymentId: Long): ResponseEntity<Map<String, Any>> {
        val payment = paymentService.getPayment(paymentId)
        return ResponseEntity(PaymentResponse.buildPaymentResponse(payment), HttpStatus.OK)
    }
}