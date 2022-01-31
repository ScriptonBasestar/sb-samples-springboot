package org.scriptonbasestar.domain.auth.dao

import org.scriptonbasestar.core.exception.DataNotFoundException
import org.scriptonbasestar.domain.auth.persistence.*
import org.scriptonbasestar.domain.auth.type.AuthorizedGrantType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
class ClientDao @Autowired constructor(
    private val clientRepository: ClientEntityRepository,
) {
    private fun exUuid(realmUuid: UUID, uuid: UUID) =
        DataNotFoundException("client for realmUuid $realmUuid, uuid $uuid is not found")

    private fun exClientId(realmUuid: UUID, clientId: String) =
        DataNotFoundException("client for realmUuid $realmUuid, clientId $clientId is not found")

    fun <T> findAll(realm: RealmEntity, page: Pageable, cb: (Page<ClientEntity>) -> Page<T>): Page<T> =
        clientRepository.findAllByRealm(realm, page).let(cb)

    fun <T> findOneByUuid(realm: RealmEntity, uuid: UUID, cb: (ClientEntity) -> T): T =
        findOneByUuid(realm, uuid, { exUuid(realm.uuid, uuid) }, cb)

    fun <T> findOneByUuid(realm: RealmEntity, uuid: UUID, ex: () -> DataNotFoundException, cb: (ClientEntity) -> T): T =
        clientRepository.findOneByRealmAndUuid(realm, uuid).orElseThrow(ex).let(cb)

    fun <T> findOneClientByClientId(realm: RealmEntity, clientId: String, cb: (ClientEntity) -> T): T =
        findOneClientByClientId(realm, clientId, { exClientId(realm.uuid, clientId) }, cb)

    fun <T> findOneClientByClientId(
        realm: RealmEntity,
        clientId: String,
        ex: () -> DataNotFoundException,
        cb: (ClientEntity) -> T
    ): T =
        clientRepository.findOneByRealmAndClientId(realm, clientId).orElseThrow(ex).let(cb)

    @Transactional(propagation = Propagation.MANDATORY)
    fun addOne(
        realm: RealmEntity,
        name: String,
        description: String,
        enabled: Boolean,
        clientId: String,
        secret: String,
        redirectUris: Set<String>,
        authorizedGrantTypes: Set<AuthorizedGrantType>,
        clientScopes: Set<String>,
    ) {
        ClientEntity(
            realm = realm,
            name = name,
            description = description,
            enabled = enabled,
            clientId = clientId,
            secret = secret,
            redirectUris = redirectUris.map(::ClientRedirectUriEntity).toSet(),
            authorizedGrantTypes = authorizedGrantTypes,
            clientScopes = clientScopes.map(::ClientScopeEntity).toSet(),
        ).let(clientRepository::save)
    }

    @Transactional(propagation = Propagation.MANDATORY)
    fun modifyOne(
        realm: RealmEntity,
        uuid: UUID,
        description: String? = null,
        enabled: Boolean? = null,
        clientId: String? = null,
        secret: String? = null,
        redirectUris: Set<String>? = null,
        authorizedGrantTypes: Set<AuthorizedGrantType>? = null,
        clientScopes: Set<String>? = null,
    ) {
        clientRepository.findOneByRealmAndUuid(realm, uuid).orElseThrow { exUuid(realm.uuid, uuid) }
            .let { clientEntity ->
                if (description != null) {
                    clientEntity.description = description
                }
                if (enabled != null) {
                    clientEntity.enabled = enabled
                }
                if (secret != null) {
                    clientEntity.secret = secret
                }
            }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    fun removeOne(
        realm: RealmEntity,
        uuid: UUID,
    ) = removeOne(realm, uuid) {
        exUuid(realm.uuid, uuid)
    }

    @Transactional(propagation = Propagation.MANDATORY)
    fun removeOne(
        realm: RealmEntity,
        uuid: UUID,
        ex: () -> DataNotFoundException
    ) {
        clientRepository.findOneByRealmAndUuid(realm, uuid).orElseThrow(ex).let(clientRepository::delete)
    }
}
