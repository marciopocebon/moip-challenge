package br.com.ms.moipchallenge.views.requests

import java.math.BigDecimal
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class BoletoPaymentRequest(
        @field:NotNull(message = "{amount.not.null}")
        @field:Digits(integer = 6, fraction = 2, message = "{amount.not.valid}")
        @field:Positive(message = "{amount.positive}")
        val amount: BigDecimal,
        @field:NotNull(message = "{client.id.not.null}")
        val clientId: Long,
        @field:NotNull(message = "{buyer.id.not.null}")
        val buyerId: Long
)