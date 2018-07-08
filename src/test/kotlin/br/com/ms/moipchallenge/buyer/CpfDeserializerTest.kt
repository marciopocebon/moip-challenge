package br.com.ms.moipchallenge.buyer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class CpfDeserializerTest {

    private val objectMapper = ObjectMapper()

    @Before
    fun setup() {
        val module = SimpleModule()
        module.addDeserializer(String::class.java, CpfDeserializer())
        objectMapper.registerModule(module)
    }

    @Test
    fun givenFormattedCpf_shouldReturnItUnformatted() {
        val json = String.format("\"534.110.630-09\"")

        assertThat(objectMapper.readValue(json, String::class.java)).isEqualTo("53411063009")
    }

    @Test
    fun givenUnformattedCpf_shouldReturnItUnformatted() {
        val json = String.format("\"53411063009\"")

        assertThat(objectMapper.readValue(json, String::class.java)).isEqualTo("53411063009")
    }
}