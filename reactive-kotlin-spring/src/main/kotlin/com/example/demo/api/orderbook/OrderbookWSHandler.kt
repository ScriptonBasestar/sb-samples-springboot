package com.example.demo.api.orderbook

import com.example.demo.api.order.OrderStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.MessageHeaders
import org.springframework.stereotype.Component
import org.springframework.util.MimeTypeUtils
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class OrderbookWSHandler(
		val orderStream: OrderStream
) : WebSocketHandler {
	override fun handle(session: WebSocketSession): Mono<Void> {
		println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
		val messageChannel = orderStream.orderRequestOut()
		messageChannel.send(MessageBuilder
				.withPayload(this)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build())
		return Mono.empty<Void>()
	}
//	eco
//	override fun handle(session: WebSocketSession): Mono<Void> {
//		return session
//				.send {
//					session.receive().map {
//						msg -> "RECEIVED ON SERVER :: " + msg.payloadAsText
//					}.map {
//						session.textMessage(it)
//					}
//				}
//	}

}