package org.scriptonbasestar.domain.auth.persistence

import org.hibernate.annotations.Nationalized
import org.scriptonbasestar.base.BaseSeqUuidEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class ClientEntity(
    @ManyToOne
    val realm: RealmEntity,
    @Nationalized
    val name: String,
    @Nationalized
    val description: String,
    val enabled: Boolean,
    @Column(name = "client_id")
    val clientId: String,
    val secret: String,
) : BaseSeqUuidEntity()

