package sample.app.web.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sample.domain.jpa.model.article.ArticleRepository

@Service
class ArticleService(
    @Autowired private val articleRepository: ArticleRepository,
)
