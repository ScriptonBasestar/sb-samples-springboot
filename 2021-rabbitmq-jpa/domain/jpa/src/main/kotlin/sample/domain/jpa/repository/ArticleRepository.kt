package sample.domain.jpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.domain.jpa.model.article.ArticleEntity

@Repository
interface ArticleRepository : JpaRepository<ArticleEntity, Long>
