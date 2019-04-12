package com.example.demo.api.order

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.MessageHeaders
import org.springframework.stereotype.Component
import org.springframework.util.MimeTypeUtils
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.time.Duration
import java.util.*


@Component
class OrderHandler(
		@Autowired
		val orderStream: OrderStream
) {

	fun buy(req: ServerRequest): Mono<ServerResponse> {
		val messageChannel = orderStream.orderRequestOut()
		return req.body(BodyExtractors.toMono(OrderHttpRequest::class.java))
				.apply {
					messageChannel.send(MessageBuilder
							.withPayload(this)
							.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
							.build())
				}.flatMap {
					ServerResponse.ok()
							.contentType(MediaType.APPLICATION_STREAM_JSON)
							.body(
									Flux.interval(Duration.ofSeconds(1))
											.flatMap { arrayListOf<OrderHttpResponse>().toFlux() },
									OrderHttpResponse::class.java
							)
				}
	}

	fun empty(req: ServerRequest): Mono<ServerResponse> {
		return OrderHttpResponse(
				uuid = UUID.randomUUID().toString(),
				success = true,
				orderType = "taker",
				precheckResult = "empty"
		).toMono()
				.flatMap { orderResult -> ServerResponse.ok().body(Mono.just(orderResult), OrderHttpResponse::class.java) }
				.switchIfEmpty(ServerResponse.notFound().build())
	}

	fun sell(req: ServerRequest): Mono<ServerResponse> {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(
						Flux.interval(Duration.ofSeconds(1))
								.flatMap { arrayListOf<OrderHttpResponse>().toFlux() },
						OrderHttpResponse::class.java
				)
	}

	fun cancel(req: ServerRequest): Mono<ServerResponse> {
		return OrderHttpResponse(
				uuid = UUID.randomUUID().toString(),
				success = true,
				orderType = "taker",
				precheckResult = ""
		).toMono()
				.flatMap { orderResult -> ServerResponse.ok().body(Mono.just(orderResult), OrderHttpResponse::class.java) }
				.switchIfEmpty(ServerResponse.notFound().build())
	}
}
