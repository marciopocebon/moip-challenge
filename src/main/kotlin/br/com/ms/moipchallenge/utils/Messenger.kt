package br.com.ms.moipchallenge.utils

import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:ValidationMessages.properties")
object Messenger {
    
    lateinit var env: Environment

    fun message(key: String) = env[key] ?: ""

}