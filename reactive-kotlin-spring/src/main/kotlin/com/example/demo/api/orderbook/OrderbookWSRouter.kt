package com.example.demo.api.orderbook

import com.example.demo.api.order.OrderStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.WebSocketService
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy


@Configuration
class OrderbookWSRouter {

	@Bean
	fun orderbookWSHandler(orderStream: OrderStream) = OrderbookWSHandler(orderStream)

	@Bean
	fun webSocketHandlerMapping(orderbookWSHandler: OrderbookWSHandler): HandlerMapping {
		val map = HashMap<String, WebSocketHandler>()
		map["/orderbook"] = orderbookWSHandler

		val mapping = SimpleUrlHandlerMapping()
		mapping.urlMap = map
		mapping.order = Ordered.HIGHEST_PRECEDENCE
//		mapping.order = -1 // before annotated controllers
		return mapping
	}

	@Bean
	fun handlerAdapter(): WebSocketHandlerAdapter {
		return WebSocketHandlerAdapter(webSocketService())
	}

	@Bean
	fun webSocketService(): WebSocketService {
		return HandshakeWebSocketService(ReactorNettyRequestUpgradeStrategy())
	}

}