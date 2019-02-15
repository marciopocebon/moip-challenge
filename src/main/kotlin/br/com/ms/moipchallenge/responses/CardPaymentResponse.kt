package br.com.ms.moipchallenge.responses

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.errors.ErrorObject
import br.com.ms.moipchallenge.models.CardPayment
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

@JsonPropertyOrder("status", "errors", "links")
class CardPaymentResponse(
        cardPayment: CardPayment,
        val errors: List<ErrorObject>?
) : ResourceSupport() {

    val status = cardPayment.status

    init {
        add(linkTo(methodOn(PaymentController::class.java).findById(cardPayment.id)).withSelfRel())
    }
}