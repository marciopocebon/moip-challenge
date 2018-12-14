package br.com.ms.moipchallenge.controllers

import br.com.ms.moipchallenge.services.PaymentService
import br.com.ms.moipchallenge.utils.annotation.PageableMethod
import br.com.ms.moipchallenge.views.requests.BoletoPaymentRequest
import br.com.ms.moipchallenge.views.requests.CardPaymentRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("boleto")
    fun saveBoletoPayment(@Valid @RequestBody request: BoletoPaymentRequest) =
            ResponseEntity(paymentService.saveBoletoPayment(request), CREATED)

    @PostMapping("card")
    fun saveCardPayment(@Valid @RequestBody request: CardPaymentRequest) =
            ResponseEntity(paymentService.saveCardPayment(request), CREATED)

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity(paymentService.findById(id), OK)

    @GetMapping
    @PageableMethod
    fun findAll(pageable: Pageable) = ResponseEntity(paymentService.findAll(pageable), OK)
}