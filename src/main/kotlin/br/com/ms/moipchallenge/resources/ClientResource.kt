package br.com.ms.moipchallenge.resources

import br.com.ms.moipchallenge.controllers.ClientController
import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.utils.Hateoas
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.data.domain.Pageable.unpaged
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class ClientResource : StdSerializer<Client>(Client::class.java), Hateoas<Client> {

    override fun serialize(client: Client, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("id", client.id)
        gen.writeStringField("name", client.name)
        gen.writeObjectField("createdAt", client.createdAt)
        gen.writeObjectField("updatedAt", client.updatedAt)
        gen.writeObjectField("_links", getLinks(client))
        gen.writeEndObject()
    }

    override fun getLinks(instance: Client) = listOf(
            linkTo(methodOn(ClientController::class.java).findById(instance.id)).withSelfRel().withType("GET"),
            linkTo(methodOn(ClientController::class.java).findAll(unpaged())).withRel("clients").withType("GET")
    )
}