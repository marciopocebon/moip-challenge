package br.com.ms.moipchallenge.payment

import br.com.ms.moipchallenge.utils.annotation.FutureDate
import org.hibernate.validator.constraints.CreditCardNumber
import java.time.LocalDate
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Embeddable
class Card(
        @field:NotBlank(message = "{card.holder.name.not.blank}")
        val holderName: String = "",
        @field:CreditCardNumber(message = "{card.number.invalid}")
        @field:NotBlank(message = "{card.number.not.blank}")
        val number: String = "",
        @field:NotNull(message = "{expiration.date.not.null}")
        @field:FutureDate(message = "{expiration.date.future}")
        val expirationDate: LocalDate? = null,
        @field:NotBlank(message = "{cvv.not.blank}")
        @field:Size(min = 3, max = 3, message = "{cvv.size}")
        val cvv: String = ""
)
