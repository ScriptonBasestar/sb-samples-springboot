package org.scriptonbasestar.domain.board.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findFirstByArticleOrderByIdDesc(article: ArticleEntity): Optional<CommentEntity>
}
