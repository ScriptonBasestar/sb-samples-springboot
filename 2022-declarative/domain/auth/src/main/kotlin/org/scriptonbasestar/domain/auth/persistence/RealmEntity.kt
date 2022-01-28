package org.scriptonbasestar.domain.auth.persistence

import org.hibernate.annotations.Nationalized
import org.scriptonbasestar.base.BaseSeqUuidEntity
import javax.persistence.Entity

@Entity
class RealmEntity(
    @Nationalized
    val name: String,
    @Nationalized
    val description: String,
    val enabled: Boolean,
) : BaseSeqUuidEntity()
