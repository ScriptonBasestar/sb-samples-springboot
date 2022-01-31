package org.scriptonbasestar.domain.auth.dao

import io.github.serpro69.kfaker.Faker
import io.mockk.InternalPlatformDsl.toStr
import org.junit.jupiter.api.*
import org.scriptonbasestar.core.exception.DataNotFoundException
import org.scriptonbasestar.domain.auth.config.AuthJpaConfig
import org.scriptonbasestar.domain.auth.config.AuthRedisConfig
import org.scriptonbasestar.domain.auth.persistence.RealmEntity
import org.scriptonbasestar.domain.auth.type.AuthorizedGrantType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*

@DataJpaTest
@Import(AuthJpaConfig::class, AuthRedisConfig::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ClientDaoTest @Autowired constructor(
    private val realmDao: RealmDao,
    private val clientDao: ClientDao,
) {
    private val faker = Faker()

    data class ClientListResponse(
        val realmUuid: UUID,
        val uuid: UUID,
        val name: String,
        val description: String,
        val enabled: Boolean,
    )

    fun getRealmPageResults() = realmDao.findAll(Pageable.ofSize(10).withPage(0)) { page ->
        page
    }

    fun getClientPageResults(realm: RealmEntity) =
        clientDao.findAll(realm, Pageable.ofSize(10).withPage(0)) { page ->
            page
        }

    @BeforeAll
    fun setUp() {
        // default realm config
        val oneRealm = realmDao.addOne(faker.animal.name(), faker.buffy.bigBads(), faker.buffy.episodes(), true)

        // default client and config
        for (i in 0..39) {
            clientDao.addOne(
                realm = oneRealm,
                name = faker.animal.name(),
                description = faker.buffy.bigBads(),
                enabled = true,
                clientId = UUID.randomUUID().toString(),
                secret = UUID.randomUUID().toString(),
                redirectUris = setOf("http://localhost:8080"),
                authorizedGrantTypes = setOf(
                    AuthorizedGrantType.AUTHORIZATION_CODE,
                    AuthorizedGrantType.CLIENT_CREDENTIALS,
                    AuthorizedGrantType.PASSWORD
                ),
                clientScopes = setOf("profile")
            )
        }
    }

    @AfterAll
    fun tearDown() {
    }

    @Test
    @Order(1)
    fun findall() {
        // given
        val oneRealm = getRealmPageResults().get().findFirst().get()
        val pageResults = getClientPageResults(oneRealm)
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
        val oneRealm = getRealmPageResults().get().findFirst().get()
        val pageResults = getClientPageResults(oneRealm)
        val anyUuid = pageResults.get().findAny().get().uuid

        // when
        clientDao.removeOne(oneRealm, anyUuid)

        // then
        Assertions.assertThrows(DataNotFoundException::class.java) {
            clientDao.findOneByUuid(oneRealm, anyUuid) {}
        }
    }

    @Test
    fun modify() {
        // given
        val oneRealm = getRealmPageResults().get().findFirst().get()
        val pageResults = getClientPageResults(oneRealm)
        val anyUuid = pageResults.get().findAny().get().uuid
        val newDescription = UUID.randomUUID().toStr()

        // when
        clientDao.modifyOne(oneRealm, anyUuid, description = newDescription)
        val clientDto = clientDao.findOneByUuid(oneRealm, anyUuid) {
            ClientListResponse(
                realmUuid = it.realm.uuid,
                uuid = it.uuid,
                name = it.name,
                description = it.description,
                enabled = it.enabled,
            )
        }

        // then
        Assertions.assertEquals(newDescription, clientDto.description)
    }
}
