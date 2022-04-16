package org.scriptonbasestar.domain.board.ds

import org.scriptonbasestar.core.exception.DataNotFoundException
import org.scriptonbasestar.core.util.nnAct
import org.scriptonbasestar.domain.board.persistence.ArticleEntity
import org.scriptonbasestar.domain.board.persistence.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
class ArticleDS @Autowired constructor(
    private val articleRepository: ArticleRepository,
) {
    private fun ex(uuid: UUID) = DataNotFoundException("article for uuid $uuid is not found")

    fun <T> findAll(page: Pageable, cb: (Page<ArticleEntity>) -> Page<T>): Page<T> =
        articleRepository.findAll(page).let(cb)

    fun <T> findOneByUuid(uuid: UUID, cb: (ArticleEntity) -> T): T =
        findOneByUuid(uuid, cb)

    fun <T> findOneByUuid(uuid: UUID, ex: () -> DataNotFoundException, cb: (ArticleEntity) -> T): T =
        articleRepository.findOneByUuid(uuid).orElseThrow { throw ex(uuid) }.let(cb)

    @Transactional(propagation = Propagation.MANDATORY)
    fun addOne(
        title: String,
        content: String,
        enabled: Boolean,
    ): ArticleEntity =
        ArticleEntity(
            title = title,
            content = content,
            enabled = enabled,
        ).let(articleRepository::save)

    @Transactional(propagation = Propagation.MANDATORY)
    fun modifyOne(
        uuid: UUID,
        title: String? = null,
        content: String? = null,
        enabled: Boolean? = null,
    ): ArticleEntity =
        modifyOne(uuid, { ex(uuid) }, title, content, enabled)

    @Transactional(propagation = Propagation.MANDATORY)
    fun modifyOne(
        uuid: UUID,
        ex: () -> DataNotFoundException,
        title: String? = null,
        content: String? = null,
        enabled: Boolean? = null,
    ): ArticleEntity =
        articleRepository.findOneByUuid(uuid).orElseThrow(ex).apply {
            nnAct(title) {
                this.title = it
            }
            nnAct(content) {
                this.content = it
            }
            nnAct(enabled) {
                this.enabled = it
            }
        }.let(articleRepository::save)

    @Transactional(propagation = Propagation.MANDATORY)
    fun removeOne(
        uuid: UUID,
    ) {
        removeOne(uuid) { ex(uuid) }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    fun removeOne(
        uuid: UUID,
        ex: () -> DataNotFoundException,
    ) {
        articleRepository.findOneByUuid(uuid).orElseThrow(ex).let(articleRepository::delete)
    }
}
