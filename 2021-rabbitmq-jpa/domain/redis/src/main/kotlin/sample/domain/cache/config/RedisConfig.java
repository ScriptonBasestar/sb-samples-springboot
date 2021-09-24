package sample.domain.cache.config;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableRedisRepositories(basePackages = "sample.domain.cache")
public class RedisConfig {
}
