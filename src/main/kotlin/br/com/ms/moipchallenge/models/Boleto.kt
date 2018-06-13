package br.com.ms.moipchallenge.models

import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable
import kotlin.streams.asSequence

@Embeddable
class Boleto {

    @Column(nullable = false)
    val number: String = randomizeBoletoNumber()
        get() {
            return StringBuilder(field)
                    .insert(5, ".")
                    .insert(11, " ")
                    .insert(17, ".")
                    .insert(23, " ")
                    .insert(29, ".")
                    .insert(35, " ")
                    .insert(37, " ")
                    .toString()
        }

    private fun randomizeBoletoNumber(): String = Random()
            .ints(45, 0, 10)
            .asSequence()
            .joinToString("")
}