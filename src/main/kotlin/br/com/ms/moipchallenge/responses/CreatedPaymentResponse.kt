package br.com.ms.moipchallenge.responses

import br.com.ms.moipchallenge.builder.ResponseBuilder
import br.com.ms.moipchallenge.error.ErrorObject
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.CardPayment

class CreatedPaymentResponse private constructor() {

    companion object {
        fun cardPaymentCreated(payment: CardPayment, errors: List<ErrorObject>) = ResponseBuilder()
                .add("status", payment.status.format).addSelfLink(payment.id)
                .apply { if (errors.isNotEmpty()) add("errors", errors) }
                .response()

        fun boletoPaymentCreated(payment: BoletoPayment) = ResponseBuilder()
                .add("number", payment.boleto.number)
                .addSelfLink(payment.id)
                .response()
    }
}