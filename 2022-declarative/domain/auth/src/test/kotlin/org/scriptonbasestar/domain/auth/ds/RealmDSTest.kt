package org.scriptonbasestar.domain.auth.ds

import io.github.serpro69.kfaker.Faker
import io.mockk.InternalPlatformDsl.toStr
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestInstance
import org.scriptonbasestar.core.exception.DataNotFoundException
import org.scriptonbasestar.domain.auth.config.AuthJpaConfig
import org.scriptonbasestar.domain.auth.config.AuthRedisConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.test.Test

@DataJpaTest
@Import(AuthJpaConfig::class, AuthRedisConfig::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class RealmDSTest @Autowired constructor(
    private val realmDao: RealmDS,
) {
    private val faker = Faker()

    @BeforeAll
    fun createRealm() {
        // default realm config
        // default client and config
        for (i in 0..39) {
            realmDao.addOne(faker.animal.name(), faker.buffy.bigBads(), faker.buffy.episodes(), true)
        }
    }

    data class RealmListResponse(
        val uuid: UUID,
        val name: String,
        val summary: String,
        val description: String,
        val enabled: Boolean,
    )

    private fun getPageResults() = realmDao.findAll(Pageable.ofSize(10).withPage(1)) { page ->
//        val totalPages = page.totalPages
//        val totalElements = page.totalElements
        page.map {
            RealmListResponse(
                uuid = it.uuid,
                name = it.name,
                summary = it.summary,
                description = it.description,
                enabled = it.enabled,
            )
        }
    }

    @Test
    @Order(1)
    fun findall() {
        // given
        val pageResults = getPageResults()
        val anyone = pageResults.get().findAny().get()
        println(anyone)
        // when
        // then
        Assertions.assertEquals(pageResults.totalElements, 40)
        Assertions.assertEquals(pageResults.totalPages, 4)
        Assertions.assertEquals(pageResults.size, 10)
    }

    @Test
    @Order(2)
    fun deleteAndFindIt_for_check_data_not_found() {
        // given
        val pageResults = getPageResults()
        val anyUuid = pageResults.get().findAny().get().uuid
        // when
        realmDao.removeOne(anyUuid)

        Assertions.assertThrows(DataNotFoundException::class.java) {
            realmDao.findOneByUuid(anyUuid) {}
        }
    }

    @Test
    fun modify() {
        // given
        val pageResults = getPageResults()
        val anyUuid = pageResults.get().findAny().get().uuid
        val newSummary = UUID.randomUUID().toStr()
        // when
        realmDao.modifyOne(anyUuid, summary = newSummary)
        val realmDto = realmDao.findOneByUuid(anyUuid) {
            RealmListResponse(
                uuid = it.uuid,
                name = it.name,
                summary = it.summary,
                description = it.description,
                enabled = it.enabled,
            )
        }
        Assertions.assertEquals(newSummary, realmDto.summary)
    }
}
