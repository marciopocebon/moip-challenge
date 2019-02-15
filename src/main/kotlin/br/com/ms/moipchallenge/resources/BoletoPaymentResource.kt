package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.models.BoletoPayment
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class BoletoPaymentResource : StdSerializer<BoletoPayment>(BoletoPayment::class.java) {

    override fun serialize(payment: BoletoPayment, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("id", payment.id)
        gen.writeObjectField("amount", payment.amount())
        gen.writeStringField("paymentType", payment.paymentType.name)
        gen.writeStringField("number", payment.number)
        gen.writeObjectField("client", payment.client)
        gen.writeObjectField("buyer", payment.buyer)
        gen.writeObjectField("_links", links(payment))
        gen.writeEndObject()
    }

    private fun links(payment: BoletoPayment) = listOf(
            linkTo(methodOn(PaymentController::class.java).findById(payment.id)).withSelfRel()
    )
}