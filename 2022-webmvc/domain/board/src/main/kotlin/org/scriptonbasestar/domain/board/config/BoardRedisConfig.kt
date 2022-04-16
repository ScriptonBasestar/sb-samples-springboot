package org.scriptonbasestar.domain.board.config

import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableTransactionManagement
@EnableRedisRepositories(basePackages = ["org.scriptonbasestar.domain.board"])
class BoardRedisConfig
