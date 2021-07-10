package com.example.demo.api.order

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class OrderRouter(private val orderHandler: OrderHandler,
				  private val messageSource: MessageSource) {
	@Bean
	fun route(): RouterFunction<ServerResponse> = router {
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
		"kkkk".nest {

		}
		resources("/**", ClassPathResource("static/"))
	}
//			.filter { request, next ->
//		next.handle(request).flatMap {
//			if (it is RenderingResponse) RenderingResponse.from(it).modelAttributes(attributes(request.locale(), messageSource)).build() else it.toMono()
//		}
//	}
//
//	private fun attributes(locale: Locale, messageSource: MessageSource) = mutableMapOf<String, Any>(
//			"i18n" to Mustache.Lambda { frag, out ->
//				val tokens = frag.execute().split("|")
//				out.write(messageSource.getMessage(tokens[0], tokens.slice(IntRange(1, tokens.size - 1)).toTypedArray(), locale))
//			})
}