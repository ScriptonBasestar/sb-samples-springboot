package com.example.demo.config

import com.example.demo.OrderHandler
import com.example.demo.Routes
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.router


//@Configuration
//@EnableWebFlux
//class WebConfig : WebFluxConfigurer {
////	@Bean
////	fun routes0(orderHandler: OrderHandler, messageSource: MessageSource): Routes {
////		return Routes(orderHandler, messageSource)
////	}
////	@Bean
////	fun routerFunctionA() = router {
////		ref<routes0.router()
////}
////	@Bean
////	fun routerFunctionA(): RouterFunction<*> {
////	RouterFunctions.toWebHandler {ref<Routes>().router(), HandlerStrategies.builder().viewResolver(ref()).build()  }
////	}
//}