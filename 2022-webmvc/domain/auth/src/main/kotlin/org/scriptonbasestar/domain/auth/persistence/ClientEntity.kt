package org.scriptonbasestar.domain.auth.persistence

import org.hibernate.annotations.Nationalized
import org.scriptonbasestar.base.BaseSeqUuidEntity
import org.scriptonbasestar.domain.auth.type.AuthorizedGrantType
import javax.persistence.*

@Entity
@Table(
    name = "TB_CLIENT",
)
class ClientEntity(
    @ManyToOne
    val realm: RealmEntity,

    @Nationalized
    val name: String,
    @Nationalized
    var description: String,
    var enabled: Boolean,
    @Column(name = "client_id", unique = true)
    val clientId: String,
    var secret: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val redirectUris: Set<ClientRedirectUriEntity>,
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "authorized_grant_types")
    val authorizedGrantTypes: Set<AuthorizedGrantType>,
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "MAP_CLIENT_SCOPE",
        joinColumns = [JoinColumn(name = "client_id")],
        inverseJoinColumns = [JoinColumn(name = "scope_id")]
    )
    val clientScopes: Set<ClientScopeEntity>
) : BaseSeqUuidEntity()
