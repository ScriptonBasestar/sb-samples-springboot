package org.scriptonbasestar.domain.auth.config

import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@EnableTransactionManagement
@EnableRedisRepositories(basePackages = ["sample.domain.auth"])
class AuthRedisConfig
