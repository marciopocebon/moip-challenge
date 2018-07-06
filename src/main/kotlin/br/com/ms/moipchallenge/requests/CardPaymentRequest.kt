package br.com.ms.moipchallenge.requests

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Card
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Client
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull

class CardPaymentRequest(
        @field:Valid
        val client: Client,
        @field:NotNull(message = "{amount.not.null}")
        @field:Digits(integer = 6, fraction = 2, message = "{amount.not.valid}")
        val amount: BigDecimal,
        @field:Valid
        val buyer: Buyer,
        val card: Card
) {

    fun toObject() = CardPayment(client, amount, buyer, card)
}