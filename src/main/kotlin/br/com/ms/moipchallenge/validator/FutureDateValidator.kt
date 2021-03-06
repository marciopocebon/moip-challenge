package br.com.ms.moipchallenge.validator

import br.com.ms.moipchallenge.annotation.FutureDate
import java.time.LocalDate
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FutureDateValidator : ConstraintValidator<FutureDate, LocalDate> {

    override fun isValid(date: LocalDate, p1: ConstraintValidatorContext) = date.isAfter(LocalDate.now())
}