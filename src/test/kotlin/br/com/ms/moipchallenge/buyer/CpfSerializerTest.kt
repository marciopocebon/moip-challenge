package br.com.ms.moipchallenge.buyer

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.StringWriter


class CpfSerializerTest {

    @Test
    fun givenNotFormattedCpf_shouldReturnItFormatted(){
        val writer = StringWriter()
        val generator = JsonFactory().createGenerator(writer)
        val provider = ObjectMapper().serializerProvider

        CpfSerializer().serialize("53411063009", generator, provider)
        generator.flush()

        assertThat(writer.toString()).isEqualTo("\"534.110.630-09\"")
    }
}