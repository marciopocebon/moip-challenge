package br.com.ms.moipchallenge.web.views.responses

import br.com.ms.moipchallenge.web.views.builders.EntityBuilder
import br.com.ms.moipchallenge.web.views.builders.ResponseBuilder
import br.com.ms.moipchallenge.payment.BoletoPayment
import br.com.ms.moipchallenge.payment.CardPayment
import br.com.ms.moipchallenge.payment.Payment
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object PaymentResponse {

    fun buildPaymentResponse(payment: Payment) =
            if (payment is BoletoPayment)
                buildBoletoPayment(payment)
            else
                buildCardPayment(payment as CardPayment)

    private fun buildCardPayment(payment: CardPayment) = buildPayment(payment, EntityBuilder()
            .add("id", payment.id)
            .add("status", payment.status.format)
            .add("holder_name", payment.card.holderName)
            .add("number", payment.card.number)
            .add("expiration_date", formatToBrazilian(payment.card.expirationDate))
            .add("cvv", payment.card.cvv))

    private fun buildBoletoPayment(payment: BoletoPayment) = buildPayment(payment, EntityBuilder()
            .add("id", payment.id)
            .add("number", payment.boleto))

    private fun buildPayment(payment: Payment, method: EntityBuilder): Map<String, Any> = ResponseBuilder()
            .add("amount", formatToBrazilian(payment.amount))
            .add("type", payment.type.format)
            .addObject("payment_method", method)
            .add("buyer", payment.buyer)
            .add("client", payment.client)
            .addSelfLink(payment.id)
            .response()

    private fun formatToBrazilian(amount: BigDecimal) =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(amount)

    private fun formatToBrazilian(date: LocalDate?) =
            date?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""

}