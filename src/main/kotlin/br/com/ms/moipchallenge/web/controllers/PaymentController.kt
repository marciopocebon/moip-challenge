package br.com.ms.moipchallenge.web.controllers

import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.web.views.requests.CardPaymentRequest
import br.com.ms.moipchallenge.web.views.responses.CreatedPaymentResponse
import br.com.ms.moipchallenge.web.views.responses.PaymentResponse
import br.com.ms.moipchallenge.web.services.PaymentService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("/boleto")
    fun createBoletoPayment(@Valid @RequestBody boletoPayment: BoletoPayment) = boletoPayment
            .let { paymentService.createBoletoPayment(it) }
            .let { ResponseEntity(CreatedPaymentResponse.boletoPaymentCreated(it), CREATED)}


    @PostMapping("/card")
    fun createCardPayment(@Valid @RequestBody request: CardPaymentRequest): ResponseEntity<Map<String, Any>> {
        val (payment, errors) = paymentService.createCardPayment(request.toObject())
        return ResponseEntity(CreatedPaymentResponse.cardPaymentCreated(payment, errors), CREATED)
    }

    @GetMapping("/{paymentId}")
    fun getPayment(@PathVariable paymentId: Long) = paymentId
            .let { paymentService.getPayment(paymentId) }
            .let { ResponseEntity(PaymentResponse.buildPaymentResponse(it), OK) }
}