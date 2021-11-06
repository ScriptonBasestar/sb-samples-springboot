package sample.domain.jpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.domain.jpa.model.article.ArticleEntity
import sample.domain.jpa.model.comment.CommentEntity
import java.util.*

@Repository
interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findFirstByArticleOrderByIdDesc(article: ArticleEntity): Optional<CommentEntity>
}
