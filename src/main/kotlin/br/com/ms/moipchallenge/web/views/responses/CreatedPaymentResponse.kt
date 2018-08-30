package br.com.ms.moipchallenge.web.views.responses

import br.com.ms.moipchallenge.exception.ErrorObject
import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.payment.CardPayment
import br.com.ms.moipchallenge.web.views.builders.ResponseBuilder

object CreatedPaymentResponse {

    fun cardPaymentCreated(payment: CardPayment, errors: List<ErrorObject>) = ResponseBuilder()
            .add("status", payment.status.format).addSelfLink(payment.id)
            .apply { if (errors.isNotEmpty()) add("errors", errors) }
            .response()

    fun boletoPaymentCreated(payment: BoletoPayment) = ResponseBuilder()
            .add("number", payment.formatBoletoNumber())
            .addSelfLink(payment.id)
            .response()
}