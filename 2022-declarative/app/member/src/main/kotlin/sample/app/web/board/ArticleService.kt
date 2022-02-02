package sample.app.web.board

import org.scriptonbasestar.domain.board.persistence.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleService(
    @Autowired private val articleRepository: ArticleRepository,
)
