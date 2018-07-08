package br.com.ms.moipchallenge.utils.annotation

import br.com.ms.moipchallenge.utils.validator.FutureDateValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [(FutureDateValidator::class)])
annotation class FutureDate(
        val message: String,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)