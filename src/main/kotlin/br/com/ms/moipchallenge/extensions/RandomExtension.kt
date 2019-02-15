package br.com.ms.moipchallenge.extensions

import java.util.*
import kotlin.streams.asSequence

fun Random.generateNumberSequence(size: Long, fromIncluded: Int, toExcluded: Int) =
        this.ints(size, fromIncluded, toExcluded)
                .asSequence()
                .joinToString("")