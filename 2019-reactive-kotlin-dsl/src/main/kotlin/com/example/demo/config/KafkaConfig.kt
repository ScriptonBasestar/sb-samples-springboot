package com.example.demo.config

import com.example.demo.OrderStream
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Configuration

@Configuration
//Sink (input-no-output)
@EnableBinding(value = [Sink::class, Source::class, OrderStream::class])
class KafkaConfig {

//	@StreamListener(Sink.INPUT)
//	fun handle(person: Person){
//		println("Received: $person")
//	}
//
//	class Person (
//		var name: String
//	)
}