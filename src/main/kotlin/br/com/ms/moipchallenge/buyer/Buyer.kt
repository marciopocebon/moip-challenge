package br.com.ms.moipchallenge.buyer

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.validator.constraints.br.CPF
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@JsonPropertyOrder(value = ["id", "name", "email", "cpf"])
data class Buyer(
        @field:NotBlank(message = "{buyer.name.not.blank}")
        @Column(nullable = false)
        val name: String,
        @field:NotBlank(message = "{buyer.email.not.blank}")
        @field:Email(message = "{buyer.email.invalid}")
        @Column(nullable = false)
        val email: String,
        @field:CPF(message = "{buyer.cpf.invalid}")
        @field:NotBlank(message = "{buyer.cpf.not.blank}")
        @JsonDeserialize(using = CpfDeserializer::class)
        @JsonSerialize(using = CpfSerializer::class)
        @Column(nullable = false)
        val cpf: String
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty(access = READ_ONLY)
    val id: Long = 0
}