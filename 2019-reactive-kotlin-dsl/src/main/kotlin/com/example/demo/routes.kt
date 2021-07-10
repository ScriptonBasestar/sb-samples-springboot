package com.example.demo

import com.samskivert.mustache.Mustache
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RenderingResponse
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.toMono
import java.util.*


class Routes(private val orderHandler: OrderHandler,
			 private val messageSource: MessageSource) {

	fun router() = org.springframework.web.reactive.function.server.router {
		accept(MediaType.TEXT_HTML).nest {
//			GET("/") { ServerResponse.ok().render("index") }
			GET("/") { ok().render("index") }
		}
		accept(MediaType.APPLICATION_JSON).nest {
			GET("/empty", orderHandler::empty)
		}
		"/order".nest {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/empty", orderHandler::empty)
			}
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/buy", orderHandler::buy)
				POST("/buy", orderHandler::buy)
			}
			accept(MediaType.TEXT_EVENT_STREAM).nest {
				POST("/sell", orderHandler::sell)
			}
			accept(MediaType.TEXT_EVENT_STREAM).nest {
				POST("/cancel/{uuid}", orderHandler::cancel)
			}
		}
		resources("/**", ClassPathResource("static/"))
	}.filter { request, next ->
		next.handle(request).flatMap {
			if (it is RenderingResponse) RenderingResponse.from(it).modelAttributes(attributes(request.locale(), messageSource)).build() else it.toMono()
		}
	}

	private fun attributes(locale: Locale, messageSource: MessageSource) = mutableMapOf<String, Any>(
			"i18n" to Mustache.Lambda { frag, out ->
				val tokens = frag.execute().split("|")
				out.write(messageSource.getMessage(tokens[0], tokens.slice(IntRange(1, tokens.size - 1)).toTypedArray(), locale))
			})
}
