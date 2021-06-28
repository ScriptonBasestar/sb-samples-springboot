package sample.app.stream

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

//@Component
//class RabbitConstructor(
//    @Autowired private val amqpAdmin: AmqpAdmin
//) {
//
//    @Value("\${config.routing-key-member-register}")
//    private lateinit var routingKeyMemberRegister: String
//
//    @Value("\${config.routing-key-member-emailauth}")
//    private lateinit var routingKeyMemberEmailauth: String
//
//    @PostConstruct
//    fun createQueues() {
//        ExchangeBuilder.topicExchange("amq.topic").build<TopicExchange>().let { exchange ->
//            amqpAdmin.declareExchange(exchange)
//            Queue("member-register-queue", true).let { queue ->
//                amqpAdmin.declareQueue(queue)
//                amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routingKeyMemberRegister))
//            }
//            Queue("member-emailauth-queue", true).let { queue ->
//                amqpAdmin.declareQueue(queue)
//                amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routingKeyMemberEmailauth))
//            }
//        }
//    }
//}
