package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.controllers.PaymentController
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.utils.Hateoas
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class BoletoPaymentResource : StdSerializer<BoletoPayment>(BoletoPayment::class.java), Hateoas<BoletoPayment> {

    override fun serialize(payment: BoletoPayment, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("id", payment.id)
        gen.writeObjectField("amount", payment.getAmount())
        gen.writeStringField("type", payment.type.name)
        gen.writeStringField("number", payment.number)
        gen.writeObjectField("client", payment.client)
        gen.writeObjectField("buyer", payment.buyer)
        gen.writeObjectField("createdAt", payment.createdAt)
        gen.writeObjectField("updatedAt", payment.updatedAt)
        gen.writeObjectField("_links", getLinks(payment))
        gen.writeEndObject()
    }

    override fun getLinks(instance: BoletoPayment) = listOf(
            linkTo(methodOn(PaymentController::class.java).findById(instance.id)).withSelfRel().withType("GET"),
            linkTo(methodOn(PaymentController::class.java).findAll(Pageable.unpaged())).withRel("payments").withType("GET")
    )
}