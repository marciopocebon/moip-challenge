package br.com.ms.moipchallenge.payment

import java.util.*
import kotlin.streams.asSequence

object BoletoFactory {

    fun generate() = Random()
            .ints(45, 0, 10)
            .asSequence()
            .joinToString("")
}