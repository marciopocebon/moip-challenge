package br.com.ms.moipchallenge.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
@JsonPropertyOrder(value = ["id", "name"])
class Client(
        @field:NotBlank(message = "{client.name.not.blank}")
        @Column(nullable = false)
        val name: String
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty(access = READ_ONLY)
    val id: Long = 0
}