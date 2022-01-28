package org.scriptonbasestar.domain.auth.persistence

import org.scriptonbasestar.base.BaseSeqUuidEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(
    name = "TB_CLIENT_REDIRECT_URI",
)
class ClientRedirectUriEntity(
    val redirectUri: String,
) : BaseSeqUuidEntity()
