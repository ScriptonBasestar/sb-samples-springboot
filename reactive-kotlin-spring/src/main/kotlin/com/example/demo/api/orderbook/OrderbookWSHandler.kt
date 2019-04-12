package com.example.demo.api.orderbook

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class OrderbookWSHandler : WebSocketHandler {
//	override fun handle(session: WebSocketSession): Mono<Void> {
//		println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
////		session.pingMessage {
////
////		}
////		session.pongMessage {
////
////		}
//		return Mono.empty<Void>()
//	}

	override fun handle(session: WebSocketSession): Mono<Void> {
		return session
				.send {
					session.receive().map {
						msg -> "RECEIVED ON SERVER :: " + msg.payloadAsText
					}.map {
						session.textMessage(it)
					}
				}
	}

}