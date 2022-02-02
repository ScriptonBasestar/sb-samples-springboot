package org.scriptonbasestar.domain.board.ds

import org.scriptonbasestar.core.exception.DataNotFoundException
import org.scriptonbasestar.core.util.nnAct
import org.scriptonbasestar.domain.board.persistence.ArticleEntity
import org.scriptonbasestar.domain.board.persistence.CommentEntity
import org.scriptonbasestar.domain.board.persistence.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
class CommentDS @Autowired constructor(
    private val commentRepository: CommentRepository,
) {
    private fun ex(articleUuid: UUID, uuid: UUID) =
        DataNotFoundException("comment for articleUuid: $articleUuid, uuid: $uuid is not found")

    fun <T> findAll(article: ArticleEntity, page: Pageable, cb: (Page<CommentEntity>) -> Page<T>): Page<T> =
        commentRepository.findAllByArticle(article, page).let(cb)

    fun <T> findOneByUuid(
        article: ArticleEntity,
        uuid: UUID,
        cb: (CommentEntity) -> T
    ): T =
        findOneByUuid(article, uuid, { ex(article.uuid, uuid) }, cb)

    fun <T> findOneByUuid(
        article: ArticleEntity,
        uuid: UUID,
        ex: () -> DataNotFoundException,
        cb: (CommentEntity) -> T
    ): T =
        commentRepository.findOneByArticleAndUuid(article, uuid).orElseThrow(ex).let(cb)

    @Transactional(propagation = Propagation.MANDATORY)
    fun addOne(
        article: ArticleEntity,
        content: String,
    ): CommentEntity =
        CommentEntity(
            article = article,
            content = content
        ).let(commentRepository::save)

    @Transactional(propagation = Propagation.MANDATORY)
    fun modifyOne(
        article: ArticleEntity,
        uuid: UUID,
        ex: () -> DataNotFoundException,
        content: String,
    ): CommentEntity =
        commentRepository.findOneByArticleAndUuid(article, uuid).orElseThrow(ex).apply {
            nnAct(content) {
                this.content = it
            }
        }.let(commentRepository::save)

    fun removeOne(
        article: ArticleEntity,
        uuid: UUID,
        ex: () -> DataNotFoundException,
    ) {
        commentRepository.findOneByArticleAndUuid(article, uuid).orElseThrow(ex).let(commentRepository::delete)
    }
}
