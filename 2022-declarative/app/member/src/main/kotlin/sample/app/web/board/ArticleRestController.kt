package sample.app.web.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class ArticleRestController(
    @Autowired private val articleService: ArticleService,
)
