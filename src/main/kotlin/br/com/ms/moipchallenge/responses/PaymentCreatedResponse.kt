package br.com.ms.moipchallenge.responses

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.errors.ErrorObject
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.CardPayment
import org.springframework.hateoas.Link
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

object PaymentCreatedResponse {

    fun boleto(payment: BoletoPayment) = mapOf(
            "number" to payment.number,
            "_links" to selfLink(payment.id))

    fun card(payment: CardPayment, errors: List<ErrorObject>?) = mapOf(
            "status" to payment.status,
            "self" to selfLink(payment.id),
            "errors" to errors
    )

    private fun selfLink(id: Long): Link = linkTo(methodOn(PaymentController::class.java).findById(id)).withSelfRel()
}