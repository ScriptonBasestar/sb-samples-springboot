package org.scriptonbasestar.domain.auth.dao

import org.scriptonbasestar.domain.auth.persistence.*
import org.scriptonbasestar.domain.auth.type.AuthorizedGrantType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import sample.core.exception.DataNotFoundException
import java.util.*

@Repository
class ClientDao @Autowired constructor(
    private val clientRepository: ClientRepository,
) {
    fun <T> findAll(page: Pageable, cb: (Page<ClientEntity>) -> Page<T>): Page<T> =
        clientRepository.findAll(page).let(cb)

    fun <T> findAllByRealmUuid(realmUuid: UUID, page: Pageable, cb: (Page<ClientEntity>) -> Page<T>): Page<T> =
        clientRepository.findAllByRealmUuid(realmUuid, page).let(cb)

    fun <T> findOneByUuid(uuid: UUID, ex: () -> DataNotFoundException, cb: (ClientEntity) -> T): T =
        clientRepository.findOneByUuid(uuid).orElseThrow(ex).let(cb)

    fun <T> findOneClientByClientId(clientId: String, ex: () -> DataNotFoundException, cb: (ClientEntity) -> T): T =
        clientRepository.findOneByClientId(clientId).orElseThrow(ex).let(cb)

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
            redirectUris = redirectUris.map { ClientRedirectUriEntity(it) }.toSet(),
            authorizedGrantTypes = authorizedGrantTypes,
            clientScopes = clientScopes.map { ClientScopeEntity(it) }.toSet(),
        ).let(clientRepository::save)
    }
}
