package sample.domain.jpa.model.comment

import org.hibernate.annotations.DynamicUpdate
import sample.domain.jpa.model.BaseEntity
import sample.domain.jpa.model.article.ArticleEntity
import javax.persistence.*

@Entity
@Table(name = "T_COMMENT")
@DynamicUpdate
class CommentEntity(
    article: ArticleEntity,
    @field:Column(
        length = 100,
        nullable = false
    )
    var content: String
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id", insertable = true, updatable = false)
    private val article: ArticleEntity? = null
    override fun toString(): String {
        return super.buildStringHelper()
            .add("article", article)
            .add("content", content)
            .toString()
    }
}
