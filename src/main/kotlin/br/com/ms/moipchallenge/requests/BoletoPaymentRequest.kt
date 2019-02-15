package br.com.ms.moipchallenge.requests

import br.com.ms.moipchallenge.AMOUNT_NOT_NULL
import br.com.ms.moipchallenge.AMOUNT_NOT_VALID
import br.com.ms.moipchallenge.AMOUNT_POSITIVE
import br.com.ms.moipchallenge.CLIENT_ID_NOT_NULL
import br.com.ms.moipchallenge.models.Buyer
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class BoletoPaymentRequest(
        @field:NotNull(message = AMOUNT_NOT_NULL)
        @field:Digits(integer = 6, fraction = 2, message = AMOUNT_NOT_VALID)
        @field:Positive(message = AMOUNT_POSITIVE)
        val amount: BigDecimal,
        @field:NotNull(message = CLIENT_ID_NOT_NULL)
        val clientId: Long,
        @field:Valid
        val buyer: Buyer
)