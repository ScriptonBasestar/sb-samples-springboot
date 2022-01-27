package sample.app.stream

// @Component
// class RabbitListener(
//
// ) {
//
//    val log = loggerUtil()
//
//    @RabbitListener(
//        bindings = [
//            QueueBinding(
//                exchange = Exchange("amq.topic"),
//                key = ["\${config.routing-key-member-register}"],
//                value = Queue("member-register-queue")
//            )
//        ]
//    )
//    fun eventRegister(message: MessageRegisterDto) {
//        log.debug("message eventRegister {}", message)
//        println(message)
//    }
//
//    @RabbitListener(
//        bindings = [
//            QueueBinding(
//                exchange = Exchange("amq.topic"),
//                key = ["\${config.routing-key-member-emailauth}"],
//                value = Queue("member-emailauth-queue")
//            )
//        ]
//    )
//    fun eventEmailauth(message: MessageEmailauthDto) {
//        log.debug("message eventEmailauth {}", message)
//        println(message)
//    }
// }
