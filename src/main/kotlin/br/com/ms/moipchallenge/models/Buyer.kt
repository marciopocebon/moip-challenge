package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.*
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
        @field:NotBlank(message = BUYER_NAME_NOT_BLANK)
        @Column(nullable = false)
        val name: String,
        @field:NotBlank(message = BUYER_EMAIL_NOT_BLANK)
        @field:Email(message = BUYER_EMAIL_INVALID)
        @Column(nullable = false, unique = true)
        val email: String,
        @field:CPF(message = BUYER_CPF_INVALID)
        @field:NotBlank(message = BUYER_CPF_NOT_BLANK)
        @Column(nullable = false)
        val cpf: String
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0
}