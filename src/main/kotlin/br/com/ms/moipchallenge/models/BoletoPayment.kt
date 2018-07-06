package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.enums.Type.BOLETO
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
    val boleto: Boleto = Boleto()
}