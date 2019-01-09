package br.com.ms.moipchallenge.models

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.hibernate.validator.constraints.br.CPF
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@JsonPropertyOrder("id", "name", "email", "cpf")
data class Buyer(
        @field:NotBlank(message = "{buyer.name.not.blank}")
        @Column(nullable = false)
        val name: String,
        @field:NotBlank(message = "{buyer.email.not.blank}")
        @field:Email(message = "{buyer.email.invalid}")
        @Column(nullable = false, unique = true)
        val email: String,
        @field:CPF(message = "{buyer.cpf.invalid}")
        @field:NotBlank(message = "{buyer.cpf.not.blank}")
        @Column(nullable = false)
        val cpf: String
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0
}