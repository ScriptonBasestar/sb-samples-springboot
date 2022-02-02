package org.scriptonbasestar.domain.board.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository :
    JpaRepository<CommentEntity, Long>,
    JpaSpecificationExecutor<CommentEntity> {
    fun findAllByArticle(article: ArticleEntity, pageable: Pageable): Page<CommentEntity>
    fun findOneByArticleAndUuid(article: ArticleEntity, uuid: UUID): Optional<CommentEntity>
}
