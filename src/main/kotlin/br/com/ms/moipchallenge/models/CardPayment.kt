package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.enums.Status.REFUSED
import br.com.ms.moipchallenge.enums.Type.CREDIT_CARD
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

@Entity
class CardPayment(client: Client,
                  amount: BigDecimal,
                  buyer: Buyer,
                  val card: Card) : Payment(CREDIT_CARD, client, amount, buyer) {

    @Enumerated(STRING)
    @Column(nullable = false)
    var status = REFUSED
}