package sample.member.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("sample.member")
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
		registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/vendor/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setWriteAcceptCharset(false);
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(stringHttpMessageConverter);
		converters.add(new SourceHttpMessageConverter<>());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		converters.add(new MappingJackson2HttpMessageConverter(mapper));
	}

//	@Autowired
//	private ApplicationContext ctx;
//	@Bean
//	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode(TemplateMode.HTML);
//		resolver.setCacheable(false);
//		resolver.setCheckExistence(true);
//		resolver.setApplicationContext(ctx);
//		return resolver;
//	}
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

//	@Bean
//	public ISpringTemplateEngine thymeleafTemplateEngine() {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
//		templateEngine.addDialect(layoutDialect());
//		return templateEngine;
//	}
}
