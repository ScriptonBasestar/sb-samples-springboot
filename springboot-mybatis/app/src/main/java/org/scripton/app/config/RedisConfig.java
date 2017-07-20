package org.scripton.app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.UnknownHostException;

/**
 * @author archmagece
 * @since 2017-09-11
 */
@Configuration
public class RedisConfig {

	@Bean
	@ConditionalOnMissingBean(name = "redisBooleanTemplate")
	public RedisTemplate<String, Boolean> redisBooleanTemplate(
			RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<String, Boolean> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

}
