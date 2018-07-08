package br.com.ms.moipchallenge.web.views.requests

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.payment.Card
import br.com.ms.moipchallenge.payment.CardPayment
import br.com.ms.moipchallenge.client.Client
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