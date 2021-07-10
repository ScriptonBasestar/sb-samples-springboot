//package com.example.demo.api.order
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.messaging.MessageHeaders
//import org.springframework.messaging.support.MessageBuilder
//import org.springframework.stereotype.Service
//import org.springframework.util.MimeTypeUtils
//import org.springframework.web.reactive.function.server.ServerRequest
//import org.springframework.web.reactive.function.server.ServerResponse
//import reactor.core.publisher.Mono
//import reactor.core.publisher.toMono
//import java.util.*
//
//
//@Service
//class OrderService(
//		@Autowired
//		val orderStream: OrderStream
//) {
//
//	fun empty(): Mono<OrderHttpResponse> {
//		return OrderHttpResponse(
//				uuid = UUID.randomUUID().toString(),
//				success = true,
//				orderType = "taker",
//				precheckResult = "empty"
//		).toMono()
//	}
//
//	fun buy(req: OrderHttpRequest): Mono<OrderHttpResponse> {
//		val messageChannel = orderStream.orderRequestIn()
//
//		messageChannel.send(MessageBuilder
//				.withPayload(req)
//				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
//				.build())
//
//		return OrderHttpResponse(
//				uuid = UUID.randomUUID().toString(),
//				success = true,
//				orderType = "taker",
//				precheckResult = "empty"
//		).toMono()
//	}
//
//	fun cancel(req: ServerRequest): Mono<ServerResponse> {
//		return OrderHttpResponse(
//				uuid = UUID.randomUUID().toString(),
//				success = true,
//				orderType = "taker",
//				precheckResult = ""
//		).toMono()
//				.flatMap { orderResult -> ServerResponse.ok().body(Mono.just(orderResult), OrderHttpResponse::class.java) }
//				.switchIfEmpty(ServerResponse.notFound().build())
//	}
//}
