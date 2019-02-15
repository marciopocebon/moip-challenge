package br.com.ms.moipchallenge.models

import br.com.ms.moipchallenge.*
import br.com.ms.moipchallenge.annotation.FutureDate
import org.hibernate.validator.constraints.CreditCardNumber
import java.time.LocalDate
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Embeddable
class Card(
        @field:NotBlank(message = CARD_HOLDER_NAME_NOT_BLANK)
        val holderName: String,
        @field:CreditCardNumber(message = CARD_NUMBER_INVALID)
        @field:NotBlank(message = CARD_NUMBER_NOT_BLANK)
        val number: String,
        @field:NotNull(message = EXPIRATION_DATE_NOT_NULL)
        @field:FutureDate(message = EXPIRATION_DATE_NOT_ALLOW_PAST_DATES)
        val expirationDate: LocalDate,
        @field:NotBlank(message = CVV_NOT_BLANK)
        @field:Size(min = 3, max = 3, message = CVV_SIZE)
        val cvv: String
)
