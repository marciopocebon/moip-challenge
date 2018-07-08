package br.com.ms.moipchallenge.buyer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

class CpfSerializer : StdSerializer<String>(String::class.java) {

    override fun serialize(value: String?, gen: JsonGenerator?, provider: SerializerProvider?) = StringBuilder(value)
            .insert(3, ".")
            .insert(7, ".")
            .insert(11, "-")
            .toString()
            .let { gen?.writeString(it) ?: Unit }
}
