package org.scriptonbasestar.domain.auth

import org.scriptonbasestar.domain.auth.config.AuthJpaConfig
import org.scriptonbasestar.domain.auth.config.AuthRedisConfig
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import kotlin.test.Test

@DataJpaTest
@Import(AuthJpaConfig::class, AuthRedisConfig::class)
class TestApplication {
    @Test
    fun test() {
        println("test?")
    }
}
