package sample.app.config

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
//class StreamConfig {
//
//    @Bean
//    fun amqpAdmin(connectionFactory: ConnectionFactory) = RabbitAdmin(connectionFactory)
//
//    @Bean
//    fun rabbitTemplate(
//        connectionFactory: ConnectionFactory,
//        messageConverter: Jackson2JsonMessageConverter
//    ) = RabbitTemplate(connectionFactory).apply {
//        setMessageConverter(messageConverter)
//    }
//
//}
