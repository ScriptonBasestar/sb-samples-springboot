package sample.app.config

// @Configuration
// class StreamConfig {
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
// }
