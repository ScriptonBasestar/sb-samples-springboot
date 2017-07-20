package org.scripton.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.scripton.app.security.NonceCheckFilter;
import org.scripton.app.security.RedisNonceCheckService;
import org.scripton.app.security.SimpleApiAuthCodeFilter;
import org.scripton.domain.config.DomainConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author archmagece
 * @since 2017-08-23
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.scripton.app")
@Import(DomainConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void  addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/error").setViewName("error");
		registry.addRedirectViewController("/swagger","swagger-ui.html");
		registry.addRedirectViewController("/doc", "/v2/api-docs");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(registry.hasMappingForPattern("/static/**")){
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
		if(registry.hasMappingForPattern("/vendor/**")){
			registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/vendor/");
		}
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ByteArrayHttpMessageConverter());
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setWriteAcceptCharset(false);
		converters.add(stringHttpMessageConverter);
		converters.add(new SourceHttpMessageConverter());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
	}

	@Value("${config.server.auth-key}")
	private String serverAuthKey;
	@Value("${config.server.auth-code}")
	private String serverAuthCode;
	@Bean
	public FilterRegistrationBean filterRegistration(){
		SimpleApiAuthCodeFilter simpleauthfilter = new SimpleApiAuthCodeFilter();
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(simpleauthfilter);
		registration.addUrlPatterns("/action/*");
		registration.addUrlPatterns("/rest/*");
		registration.addInitParameter("config.server.auth-key", serverAuthKey);
		registration.addInitParameter("config.server.auth-code", serverAuthCode);
		registration.setName("simpleApiAuthCodeFilter");
		registration.setOrder(3);
		return registration;
	}

	@Bean
	public RedisNonceCheckService redisNonceCheckService(){
		return new RedisNonceCheckService();
	}

	@Value("${config.server.auth-nonce-name}")
	private String nonceName;
	@Value("${config.server.auth-nonce-error-message}")
	private String nonceErrorMessage;
	@Bean
	public FilterRegistrationBean nonceCheckFilter(){
		NonceCheckFilter filter = new NonceCheckFilter();
		filter.setNonceCheckService(redisNonceCheckService());
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.addUrlPatterns("/action/*");
		registration.addUrlPatterns("/rest/*");
		registration.addInitParameter("config.server.auth-nonce-name", nonceName);
		registration.addInitParameter("config.server.auth-nonce-error-message", nonceErrorMessage);
		registration.setName("nonceCheckFilter");
		registration.setOrder(2);
		return registration;
	}

}
