package org.scriptonbasestar.domain.auth.persistence

import org.hibernate.annotations.Nationalized
import org.scriptonbasestar.base.BaseSeqUuidEntity
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
