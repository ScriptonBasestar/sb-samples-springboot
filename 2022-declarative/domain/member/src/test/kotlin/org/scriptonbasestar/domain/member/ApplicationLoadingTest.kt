package org.scriptonbasestar.domain.member

import org.scriptonbasestar.domain.member.config.MemberJpaConfig
import org.scriptonbasestar.domain.member.config.MemberRedisConfig
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import kotlin.test.Test

@DataJpaTest
@Import(MemberJpaConfig::class, MemberRedisConfig::class)
class ApplicationLoadingTest {
    @Test
    fun test() {
        println("test?")
    }
}
