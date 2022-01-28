package org.scriptonbasestar.domain.auth.dao

import org.scriptonbasestar.domain.auth.persistence.RealmEntity
import org.scriptonbasestar.domain.auth.persistence.RealmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import sample.core.exception.DataNotFoundException
import java.util.*

@Repository
class RealmDao @Autowired constructor(
    private val realmRepository: RealmRepository,
) {
    fun <T> findAll(page: Pageable, cb: (Page<RealmEntity>) -> Page<T>): Page<T> {
        realmRepository.findAll(page).let {
            return cb(it)
        }
    }

    fun <T> findOneByUuid(uuid: UUID, ex: () -> DataNotFoundException, cb: (RealmEntity) -> T): T {
        return realmRepository.findOneByUuid(uuid).orElseThrow {
            throw ex()
        }.let(cb)
//        realmRepository.flush()
//        cb에서 값을 변경하면 저장이 될까?
    }

    fun addOne(
        name: String,
        description: String,
        enabled: Boolean,
    ) {
        RealmEntity(
            name = name,
            description = description,
            enabled = enabled,
        ).let(realmRepository::save)
    }

    fun modifyOne(
        uuid: UUID,
        ex: () -> DataNotFoundException,
        description: String?,
        enabled: Boolean?,
    ) {
        realmRepository.findOneByUuid(uuid).orElseThrow {
            throw ex()
        }.let { realmEntity ->
            description?.let {
                realmEntity.description = description
            }
            enabled?.let {
                realmEntity.enabled = enabled
            }
        }
    }

    fun removeOne(
        uuid: UUID,
        ex: () -> DataNotFoundException,
    ) {
        realmRepository.findOneByUuid(uuid).orElseThrow {
            throw ex()
        }.let(realmRepository::delete)
    }
}
