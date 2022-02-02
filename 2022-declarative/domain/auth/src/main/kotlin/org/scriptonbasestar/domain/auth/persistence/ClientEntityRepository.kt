package org.scriptonbasestar.domain.auth.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientEntityRepository :
    JpaRepository<ClientEntity, Long>,
    JpaSpecificationExecutor<ClientEntity> {
    fun findAllByRealm(realm: RealmEntity, pageable: Pageable): Page<ClientEntity>
    fun findOneByRealmAndUuid(realm: RealmEntity, uuid: UUID): Optional<ClientEntity>
    fun findOneByRealmAndClientId(realm: RealmEntity, clientId: String): Optional<ClientEntity>
}
