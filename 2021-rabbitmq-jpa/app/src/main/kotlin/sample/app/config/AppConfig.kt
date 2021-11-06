package sample.app.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter(objectMapper())
}
