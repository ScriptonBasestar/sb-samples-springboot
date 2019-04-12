package com.example.demo.api.orderbook

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService
import org.springframework.web.reactive.socket.server.WebSocketService
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import javax.websocket.OnMessage
import javax.websocket.OnClose
import javax.websocket.OnOpen
import javax.websocket.server.ServerEndpoint





@Configuration
class OrderbookWSRouter {

	@Bean
	fun webSocketHandlerMapping(): HandlerMapping {
		val map = HashMap<String, WebSocketHandler>()
		map["/orderbook"] = OrderbookWSHandler()

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


	@ServerEndpoint("/333")
	class SocketProxy {

		@OnOpen
		fun onOpen() {
			println("onOpen")
		}

		@OnClose
		fun onClose() {
			println("onClose")
		}

		@OnMessage
		fun onMessage(message: String) {
			println("onMessage:$message")
		}

	}
}