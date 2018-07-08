package br.com.ms.moipchallenge.payment

enum class Type(val format: String) {

    BOLETO("Boleto"), CREDIT_CARD("Credit card");
}