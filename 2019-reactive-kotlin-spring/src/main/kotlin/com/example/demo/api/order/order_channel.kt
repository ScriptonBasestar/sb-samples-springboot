package com.example.demo.api.order

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.http.MediaType
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
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

data class OrderHttpRequest(
		val uuid:String,
		val success:Boolean,
		val orderType:String,
		val precheckResult:String
)
data class OrderEngineRequest(
		val uuid:String,
		val success:Boolean,
		val orderType:String,
		val precheckResult:String
)
data class OrderEngineResponse(
		val uuid:String,
		val success:Boolean,
		val orderType:String,
		val precheckResult:String
)
data class OrderHttpResponse(
		val uuid:String,
		val success:Boolean,
		val orderType:String,
		val precheckResult:String
)

interface OrderStream {
	@Input("test_topic-in")
	fun orderRequestIn(): SubscribableChannel
	@Output("test_topic-out")
	fun orderRequestOut(): MessageChannel
}

