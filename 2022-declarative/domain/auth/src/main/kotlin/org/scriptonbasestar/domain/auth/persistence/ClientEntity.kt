package org.scriptonbasestar.domain.auth.persistence

import org.hibernate.annotations.Nationalized
import org.scriptonbasestar.base.BaseSeqUuidEntity
import org.scriptonbasestar.domain.auth.type.AuthorizedGrantType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*
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
    val description: String,
    val enabled: Boolean,
    @Column(name = "client_id", unique = true)
    val clientId: String,
    val secret: String,
    @OneToMany
    val redirectUris: Set<ClientRedirectUriEntity>,
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "authorized_grant_types")
    val authorizedGrantTypes: Set<AuthorizedGrantType>,
    @ManyToMany
    @JoinTable(
        name = "MAP_CLIENT_SCOPE",
        joinColumns = [JoinColumn(name = "client_id")],
        inverseJoinColumns = [JoinColumn(name = "scope_id")]
    )
    val clientScopes: Set<ClientScopeEntity>
) : BaseSeqUuidEntity()

@Repository
interface ClientRepository :
    JpaRepository<ClientEntity, Long>,
    JpaSpecificationExecutor<ClientEntity> {
    fun findAllByRealmUuid(realmUuid: UUID, pageable: Pageable): Page<ClientEntity>
    fun findOneByUuid(uuid: UUID): Optional<ClientEntity>
    fun findOneByClientId(clientId: String): Optional<ClientEntity>
}
