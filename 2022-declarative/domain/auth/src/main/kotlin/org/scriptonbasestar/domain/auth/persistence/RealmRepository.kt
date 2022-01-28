package org.scriptonbasestar.domain.auth.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RealmRepository :
    JpaRepository<RealmEntity, Long>,
    JpaSpecificationExecutor<RealmEntity> {
    fun findOneByUuid(uuid: UUID): Optional<RealmEntity>
}
