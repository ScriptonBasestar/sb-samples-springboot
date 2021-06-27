package sample.app.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate


@Configuration
class RestClientConfig {

    @Bean
    fun restTemplate(
        builder: RestTemplateBuilder,
    ): RestTemplate = builder
        .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
        .build()

}
