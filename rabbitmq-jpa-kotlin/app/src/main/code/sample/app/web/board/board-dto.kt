package sample.app.web.board

data class ArticleDto(
    val id: Long,
    val title: String,
    val content: String,
)

data class ArticleReqDto(
    val title: String?,
    val content: String?,
)



data class CommentDto(
    val id: Long,
    val content: String,
)

data class CommentReqDto(
    val content: String?,
)
