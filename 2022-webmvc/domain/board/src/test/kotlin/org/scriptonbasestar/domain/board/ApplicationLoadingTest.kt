package org.scriptonbasestar.domain.board

import org.scriptonbasestar.domain.board.config.BoardJpaConfig
import org.scriptonbasestar.domain.board.config.BoardRedisConfig
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import kotlin.test.Test

@DataJpaTest
@Import(BoardJpaConfig::class, BoardRedisConfig::class)
class ApplicationLoadingTest {
    @Test
    fun test() {
        println("test?")
    }
}
