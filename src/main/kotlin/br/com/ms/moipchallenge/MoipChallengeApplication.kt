package br.com.ms.moipchallenge

import br.com.ms.moipchallenge.utils.Messenger
import io.sentry.spring.SentryExceptionResolver
import io.sentry.spring.SentryServletContextInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@SpringBootApplication
class MoipChallengeApplication{

    @Bean
    fun sentryExceptionResolver() = SentryExceptionResolver()

    @Bean
    fun sentryServletContextInitializer() = SentryServletContextInitializer()
}

fun main(args: Array<String>) {
    val env = runApplication<MoipChallengeApplication>(*args).getBean(Environment::class.java)
    Messenger.env = env
}
