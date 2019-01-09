package br.com.ms.moipchallenge.controllers

import br.com.ms.moipchallenge.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.requests.CardPaymentRequest
import br.com.ms.moipchallenge.responses.PaymentCreatedResponse
import br.com.ms.moipchallenge.services.PaymentService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/payments")
class PaymentController(
        private val paymentService: PaymentService
) {

    @PostMapping("boleto")
    fun saveBoletoPayment(@Valid @RequestBody request: BoletoPaymentRequest) =
            ResponseEntity(PaymentCreatedResponse.boleto(paymentService.saveBoletoPayment(request)), CREATED)

    @PostMapping("card")
    fun saveCardPayment(@Valid @RequestBody request: CardPaymentRequest): ResponseEntity<Map<String, Any?>> {
        val (status, errors) = paymentService.saveCardPayment(request)

        return ResponseEntity(PaymentCreatedResponse.card(status, errors), CREATED)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity(paymentService.findById(id), OK)
}