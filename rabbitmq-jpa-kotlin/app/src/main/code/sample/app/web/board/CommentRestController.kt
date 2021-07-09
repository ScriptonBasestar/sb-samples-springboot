package sample.app.web.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.app.web.ErrorCode
import sample.core.transfer.PageResponseWrapper
import sample.domain.model.CommentEntity

@RestController
@RequestMapping("/board/{article_id}")
class CommentRestController(
    @Autowired private val commentService: CommentService,
) {

    @GetMapping("/{comment_id}")
    fun list(
        @PathVariable("article_id") articleId: Long,
        @PathVariable("comment_id") commentId: Long,
        @PageableDefault pageable: Pageable
    ) = commentService.list(pageable).apply {
        PageResponseWrapper.create<CommentEntity>()
            .data(content)
            .totalPages(totalPages)
            .totalElements(totalElements)
            .code(ErrorCode.SUCCESS.name)
            .success(true)
    }

}
