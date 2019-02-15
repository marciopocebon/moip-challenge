package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.models.CardPayment
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class CardPaymentResource : StdSerializer<CardPayment>(CardPayment::class.java) {

    override fun serialize(payment: CardPayment, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("id", payment.id)
        gen.writeObjectField("amount", payment.amount())
        gen.writeStringField("paymentType", payment.paymentType.name)
        gen.writeObjectField("status", payment.status)
        gen.writeObjectField("card", payment.card)
        gen.writeObjectField("client", payment.client)
        gen.writeObjectField("buyer", payment.buyer)
        gen.writeObjectField("_links", getLinks(payment))
        gen.writeEndObject()
    }

    private fun getLinks(payment: CardPayment) = listOf(
            linkTo(methodOn(PaymentController::class.java).findById(payment.id)).withSelfRel()
    )
}