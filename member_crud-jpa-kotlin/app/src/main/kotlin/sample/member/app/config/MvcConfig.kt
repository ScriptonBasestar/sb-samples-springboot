package sample.member.app.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.ByteArrayHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter
import org.springframework.http.converter.xml.SourceHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.xml.transform.Source

@Configuration
@EnableWebMvc
@ComponentScan("sample.member")
class MvcConfig : WebMvcConfigurer {

	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/")
		registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/vendor/")
	}

	override fun addViewControllers(registry: ViewControllerRegistry) {
		registry.addViewController("/").setViewName("index.html")
		registry.addViewController("/login").setViewName("login.html")
	}

	override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
		val stringHttpMessageConverter = StringHttpMessageConverter()
		stringHttpMessageConverter.setWriteAcceptCharset(false)
		converters.add(ByteArrayHttpMessageConverter())
		converters.add(stringHttpMessageConverter)
		converters.add(SourceHttpMessageConverter<Source>())
		converters.add(AllEncompassingFormHttpMessageConverter())
		val mapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
		mapper.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
		converters.add(MappingJackson2HttpMessageConverter(mapper))
	}

}
