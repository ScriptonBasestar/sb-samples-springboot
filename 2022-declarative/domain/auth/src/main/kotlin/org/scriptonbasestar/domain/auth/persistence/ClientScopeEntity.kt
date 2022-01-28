package org.scriptonbasestar.domain.auth.persistence

import org.scriptonbasestar.base.BaseSeqUuidEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(
    name = "TB_CLIENT_SCOPE",
)
class ClientScopeEntity(
    val scope: String,
) : BaseSeqUuidEntity()
