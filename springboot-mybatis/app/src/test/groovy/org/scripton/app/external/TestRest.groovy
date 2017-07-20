package org.scripton.app.external

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

/**
 * @author archmagece
 * @since 2017-08-25
 */
class TestRest {
	@Test
	void test() {
		print(new RestTemplateBuilder().defaultMessageConverters())

		RestTemplate restTemplate = new RestTemplate()
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters()
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper()
				System.out.println(objectMapper)
//				((MappingJackson2HttpMessageConverter) converter).setObjectMapper();
			}
		}
	}
}
