package org.scriptonbasestar.domain.auth.ds

import org.scriptonbasestar.core.exception.DataNotFoundException
import org.scriptonbasestar.domain.auth.persistence.RealmEntity
import org.scriptonbasestar.domain.auth.persistence.RealmEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
class RealmDS @Autowired constructor(
    private val realmRepository: RealmEntityRepository,
) {
    private fun ex(uuid: UUID) =
        DataNotFoundException("realm for uuid $uuid is not found")

    fun <T> findAll(page: Pageable, cb: (Page<RealmEntity>) -> Page<T>): Page<T> {
        realmRepository.findAll(page).let {
            return cb(it)
        }
    }

    fun <T> findOneByUuid(uuid: UUID, cb: (RealmEntity) -> T): T =
        findOneByUuid(uuid, { ex(uuid) }, cb)

    fun <T> findOneByUuid(uuid: UUID, ex: () -> DataNotFoundException, cb: (RealmEntity) -> T): T {
        return realmRepository.findOneByUuid(uuid).orElseThrow {
            throw ex()
        }.let(cb)
//        realmRepository.flush()
//        cb에서 entity 값을 변경하면 저장이 될까? 안되야하는데
    }

    @Transactional(propagation = Propagation.MANDATORY)
    fun addOne(
        name: String,
        summary: String,
        description: String,
        enabled: Boolean,
    ): RealmEntity =
        RealmEntity(
            name = name,
            summary = summary,
            description = description,
            enabled = enabled,
        ).let(realmRepository::save)

    @Transactional(propagation = Propagation.MANDATORY)
    fun modifyOne(
        uuid: UUID,
        summary: String? = null,
        description: String? = null,
        enabled: Boolean? = null,
    ) = modifyOne(uuid, { ex(uuid) }, summary, description, enabled)

    @Transactional(propagation = Propagation.MANDATORY)
    fun modifyOne(
        uuid: UUID,
        ex: () -> DataNotFoundException,
        summary: String? = null,
        description: String? = null,
        enabled: Boolean? = null,
    ): RealmEntity =
        realmRepository.findOneByUuid(uuid).orElseThrow(ex).let { realmEntity ->
            summary?.let {
                realmEntity.summary = summary
            }
            description?.let {
                realmEntity.description = description
            }
            enabled?.let {
                realmEntity.enabled = enabled
            }
            realmEntity
        }

    @Transactional(propagation = Propagation.MANDATORY)
    fun removeOne(
        uuid: UUID,
    ) = removeOne(uuid) { ex(uuid) }

    @Transactional(propagation = Propagation.MANDATORY)
    fun removeOne(
        uuid: UUID,
        ex: () -> DataNotFoundException,
    ) {
        realmRepository.findOneByUuid(uuid).orElseThrow(ex).let(realmRepository::delete)
    }
}
