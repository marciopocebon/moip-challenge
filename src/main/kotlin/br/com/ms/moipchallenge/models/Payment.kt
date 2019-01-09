package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.enums.Type
import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP
import javax.persistence.*
import javax.persistence.CascadeType.PERSIST
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.InheritanceType.JOINED
import javax.validation.Valid

@Entity
@Inheritance(strategy = JOINED)
abstract class Payment(
        @Enumerated(STRING)
        @Column(nullable = false)
        val type: Type,
        @field:Valid
        @ManyToOne(optional = false, cascade = [PERSIST])
        val client: Client,
        @Column(nullable = false)
        private val amount: BigDecimal,
        @field:Valid
        @ManyToOne(optional = false, cascade = [PERSIST])
        val buyer: Buyer
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0

    fun amount(): BigDecimal = amount.setScale(2, HALF_UP)
}