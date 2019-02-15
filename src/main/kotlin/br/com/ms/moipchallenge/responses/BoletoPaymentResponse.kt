package br.com.ms.moipchallenge.responses

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.models.BoletoPayment
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

@JsonPropertyOrder("number", "links")
class BoletoPaymentResponse(
        boletoPayment: BoletoPayment
) : ResourceSupport() {

    val number = boletoPayment.number

    init {
        add(linkTo(methodOn(PaymentController::class.java).findById(boletoPayment.id)).withSelfRel())
    }
}