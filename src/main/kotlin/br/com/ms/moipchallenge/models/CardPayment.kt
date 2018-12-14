package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.enums.Type.CREDIT_CARD
import br.com.ms.moipchallenge.resources.CardPaymentResource
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity

@Entity
@JsonSerialize(using = CardPaymentResource::class)
class CardPayment(
        amount: BigDecimal,
        client: Client,
        buyer: Buyer,
        val card: Card
) : Payment(CREDIT_CARD, client, amount, buyer) {

    @Column(nullable = false)
    val status = "APPROVED"
}