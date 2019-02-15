package br.com.ms.moipchallenge

import io.sentry.spring.SentryExceptionResolver
import io.sentry.spring.SentryServletContextInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MoipChallengeApplication{

    @Bean
    fun sentryExceptionResolver() = SentryExceptionResolver()

    @Bean
    fun sentryServletContextInitializer() = SentryServletContextInitializer()
}

fun main(args: Array<String>) {
    runApplication<MoipChallengeApplication>(*args)
}
