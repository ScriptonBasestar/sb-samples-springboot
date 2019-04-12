package com.example.demo

import com.samskivert.mustache.Mustache
import com.example.demo.view.MustacheResourceTemplateLoader
import com.example.demo.view.MustacheViewResolver
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunctions


var beans = org.springframework.context.support.beans {
	bean<OrderHandler>()
	bean<Routes>()
	bean("webHandler") {
		RouterFunctions.toWebHandler(ref<Routes>().router(), HandlerStrategies.builder().viewResolver(ref()).build())
	}
	bean("messageSource") {
		ReloadableResourceBundleMessageSource().apply {
			setBasename("messages")
			setDefaultEncoding("UTF-8")
		}
	}
	bean {
		val prefix = "classpath:/templates/"
		val suffix = ".mustache"
		val loader = MustacheResourceTemplateLoader(prefix, suffix)
		MustacheViewResolver(Mustache.compiler().withLoader(loader)).apply {
			setPrefix(prefix)
			setSuffix(suffix)
		}
	}
//	beans {
//		val streamsConfiguration = Properties()
//		streamsConfiguration.put(
//				StreamsConfig.APPLICATION_ID_CONFIG,
//				"wordcount-live-test")
////	val topology = StreamsBuilder().stream<>("test_topic").
//		val streams = KafkaStreams(KStreamBuilder()., streamsConfiguration)
//	}
	profile("cors") {
		bean("corsFilter") {
			CorsWebFilter { CorsConfiguration().applyPermitDefaultValues() }
		}
	}
//	bean("transactionManager") {
//		TransactionManager(dataSource)
//				.also {
//					logger.info { "=== USE SQL datasource: user=${dataSource.username} url=${dataSource.jdbcUrl}" }
//				}
//	}
//	bean("persistenceExceptionTranslationPostProcessor"){
//		PersistenceExceptionTranslationPostProcessor().apply {
//		}
//	}
}

