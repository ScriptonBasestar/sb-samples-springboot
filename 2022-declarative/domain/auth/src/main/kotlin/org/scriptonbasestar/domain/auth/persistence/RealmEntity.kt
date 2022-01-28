package org.scriptonbasestar.domain.auth.persistence

import org.hibernate.annotations.Nationalized
import org.scriptonbasestar.base.BaseSeqUuidEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(
    name = "TB_REALM",
)
class RealmEntity(
    @Nationalized
    val name: String,
    @Nationalized
    var description: String,
    var enabled: Boolean,
) : BaseSeqUuidEntity()

@Repository
interface RealmRepository :
    JpaRepository<RealmEntity, Long>,
    JpaSpecificationExecutor<RealmEntity> {
    fun findOneByUuid(uuid: UUID): Optional<RealmEntity>
}
