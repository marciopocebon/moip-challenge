package br.com.ms.moipchallenge.payment

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import br.com.ms.moipchallenge.payment.Type.BOLETO
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class BoletoPayment(
        client: Client,
        amount: BigDecimal,
        buyer: Buyer
) : Payment(BOLETO, client, amount, buyer) {

    @JsonProperty(access = READ_ONLY)
    val boleto: String = BoletoFactory.generate()

    fun formatBoletoNumber() = StringBuilder(boleto)
            .insert(5, ".")
            .insert(11, " ")
            .insert(17, ".")
            .insert(23, " ")
            .insert(29, ".")
            .insert(35, " ")
            .insert(37, " ")
            .toString()

    override fun toString() = "BoletoPayment(client=$client, amount=$amount, buyer=$buyer, boleto=$boleto)"
}