package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.enums.Type.BOLETO
import br.com.ms.moipchallenge.resources.BoletoPaymentResource
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import kotlin.streams.asSequence

@Entity
@JsonSerialize(using = BoletoPaymentResource::class)
class BoletoPayment(
        amount: BigDecimal,
        client: Client,
        buyer: Buyer
) : Payment(BOLETO, client, amount, buyer) {

    @JsonProperty(access = READ_ONLY)
    val number: String = Random()
            .ints(45, 0, 10)
            .asSequence()
            .joinToString("")
}