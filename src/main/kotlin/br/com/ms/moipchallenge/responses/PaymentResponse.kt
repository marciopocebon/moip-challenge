package br.com.ms.moipchallenge.responses

import br.com.ms.moipchallenge.builder.EntityBuilder
import br.com.ms.moipchallenge.builder.ResponseBuilder
import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.CardPayment
import br.com.ms.moipchallenge.models.Payment
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class PaymentResponse private constructor() {

    companion object {
        fun buildPaymentResponse(payment: Payment) =
                if (payment is BoletoPayment) buildBoletoPayment(payment) else buildCardPayment(payment as CardPayment)

        private fun buildCardPayment(payment: CardPayment) = buildPayment(payment, EntityBuilder()
                .add("id", payment.id)
                .add("status", payment.status.format)
                .add("holder_name", payment.card.holderName)
                .add("number", payment.card.number)
                .add("expiration_date", formatToBrazillian(payment.card.expirationDate))
                .add("cvv", payment.card.cvv))

        private fun buildBoletoPayment(payment: BoletoPayment) = buildPayment(payment, EntityBuilder()
                .add("id", payment.id)
                .add("number", payment.boleto.number))

        private fun buildPayment(payment: Payment, method: EntityBuilder) = ResponseBuilder()
                .add("amount", formatToBrazillian(payment.amount))
                .add("type", payment.type.format)
                .addObject("payment_method", method)
                .add("buyer", payment.buyer)
                .add("client", payment.client)
                .addSelfLink(payment.id)
                .response()

        private fun formatToBrazillian(amount: BigDecimal) =
                NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(amount)

        private fun formatToBrazillian(date: LocalDate?) =
                date?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
    }
}