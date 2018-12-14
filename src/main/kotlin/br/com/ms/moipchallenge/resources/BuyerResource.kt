package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.controllers.BuyerController
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.utils.Hateoas
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.data.domain.Pageable.unpaged
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class BuyerResource : StdSerializer<Buyer>(Buyer::class.java), Hateoas<Buyer> {

    override fun serialize(buyer: Buyer, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("id", buyer.id)
        gen.writeStringField("name", buyer.name)
        gen.writeStringField("email", buyer.email)
        gen.writeStringField("cpf", buyer.cpf)
        gen.writeObjectField("createdAt", buyer.createdAt)
        gen.writeObjectField("updatedAt", buyer.updatedAt)
        gen.writeObjectField("_links", getLinks(buyer))
        gen.writeEndObject()
    }

    override fun getLinks(instance: Buyer) = listOf(
            linkTo(methodOn(BuyerController::class.java).findById(instance.id)).withSelfRel().withType("GET"),
            linkTo(methodOn(BuyerController::class.java).findAll(unpaged())).withRel("clients").withType("GET")
    )
}