package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.resources.ClientResource
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
@JsonSerialize(using = ClientResource::class)
data class Client(
        @field:NotBlank(message = "{client.name.not.blank}")
        @Column(nullable = false)
        val name: String
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0

    @JsonProperty(access = READ_ONLY)
    val createdAt = LocalDateTime.now()
    @JsonProperty(access = READ_ONLY)
    val updatedAt = LocalDateTime.now()

}