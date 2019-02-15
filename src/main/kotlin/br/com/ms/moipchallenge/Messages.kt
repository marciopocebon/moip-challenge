package br.com.ms.moipchallenge

//CLIENT
const val CLIENT_ID_NOT_NULL = "Client ID cannot be null"

//BUYER
const val BUYER_CPF_NOT_BLANK = "Buyer cpf can not be blank"
const val BUYER_CPF_INVALID = "Buyer cpf must be valid"
const val BUYER_NAME_NOT_BLANK = "Buyer name can not be blank"
const val BUYER_EMAIL_NOT_BLANK = "Buyer email can not be blank"
const val BUYER_EMAIL_INVALID = "Buyer email must be valid"

//PAYMENT
const val AMOUNT_NOT_VALID = "Payment amount must be valid. The maximum allowed value is composed of 6 integers and 2 decimal places"
const val AMOUNT_NOT_NULL = "Payment amount can not be null"
const val AMOUNT_POSITIVE = "Payment amount must be a positive value"
const val PAYMENT_NOT_FOUND = "Payment with requested ID wasn't found"

//CARD
const val CARD_HOLDER_NAME_NOT_BLANK = "Card holder name can not be blank"
const val CARD_NUMBER_NOT_BLANK = "Card number can not be blank"
const val CARD_NUMBER_INVALID = "Card number must be valid"
const val EXPIRATION_DATE_NOT_NULL = "Card expiration can not be null"
const val EXPIRATION_DATE_NOT_ALLOW_PAST_DATES = "Card expiration date must be a future date"
const val CVV_NOT_BLANK = "Card cvv can not be blank"
const val CVV_SIZE = "Card cvv must have length equal 3"

//EXCEPTIONS
const val NOT_FOUND_GENERAL = "Entity not found"