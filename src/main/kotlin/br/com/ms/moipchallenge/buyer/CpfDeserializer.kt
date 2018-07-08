package br.com.ms.moipchallenge.buyer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.TextNode

class CpfDeserializer : StdDeserializer<String>(String::class.java) {

    override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?) = parser
            ?.let { it.codec.readTree<TextNode>(it) }?.textValue()?.replace("[^0-9]+".toRegex(), "")
}