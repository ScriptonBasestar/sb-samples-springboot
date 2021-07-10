package org.scripton.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/org/scripton").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/join").setViewName("join");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		라이브러리로 bootstrap 추가시
//		if (!registry.hasMappingForPattern("/webjars/**")) {
//			registry.addResourceHandler("/webjars/**")
//					.addResourceLocations("classpath:/META-INF/resources/webjars/");
//		}
		if (!registry.hasMappingForPattern("/static/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
		if (!registry.hasMappingForPattern("/vendor/**")) {
			registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/vendor/");
		}
	}
}
