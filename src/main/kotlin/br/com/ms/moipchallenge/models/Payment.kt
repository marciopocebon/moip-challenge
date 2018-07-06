package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.enums.Type
import java.math.BigDecimal
import javax.persistence.*
import javax.persistence.CascadeType.PERSIST
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.InheritanceType.JOINED
import javax.validation.Valid
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
@Inheritance(strategy = JOINED)
abstract class Payment(
        @Enumerated(STRING)
        @Column(nullable = false)
        val type: Type,
        @field:Valid
        @ManyToOne(optional = false, cascade = [PERSIST])
        val client: Client,
        @field:NotNull(message = "{amount.not.null}")
        @field:Digits(integer = 6, fraction = 2, message = "{amount.not.valid}")
        @field:Positive(message = "{amount.positive}")
        @Column(nullable = false)
        val amount: BigDecimal,
        @field:Valid
        @ManyToOne(optional = false, cascade = [PERSIST])
        val buyer: Buyer
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0
}