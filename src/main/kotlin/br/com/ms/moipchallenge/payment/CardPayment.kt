package br.com.ms.moipchallenge.payment

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.Status.REFUSED
import br.com.ms.moipchallenge.payment.Type.CREDIT_CARD
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

@Entity
class CardPayment(
        client: Client,
        amount: BigDecimal,
        buyer: Buyer,
        val card: Card
) : Payment(CREDIT_CARD, client, amount, buyer) {

    @Enumerated(STRING)
    @Column(nullable = false)
    var status = REFUSED

    override fun toString() = "CardPayment(client=$client, amount=$amount, buyer=$buyer, card=$card, status= $status)"
}