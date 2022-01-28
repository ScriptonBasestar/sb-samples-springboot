package org.scriptonbasestar.domain.auth.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository :
    JpaRepository<ClientEntity, Long>,
    JpaSpecificationExecutor<ClientEntity> {
    fun findAllByRealmUuid(realmUuid: UUID, pageable: Pageable): Page<ClientEntity>
    fun findOneByUuid(uuid: UUID): Optional<ClientEntity>
    fun findOneByClientId(clientId: String): Optional<ClientEntity>
}
