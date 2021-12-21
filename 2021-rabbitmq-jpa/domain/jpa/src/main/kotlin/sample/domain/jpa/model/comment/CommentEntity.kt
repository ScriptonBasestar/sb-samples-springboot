package sample.domain.jpa.model.comment

import org.hibernate.annotations.DynamicUpdate
import sample.domain.jpa.model.BaseEntity
import sample.domain.jpa.model.BaseSeqEntity
import sample.domain.jpa.model.article.ArticleEntity
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "T_COMMENT")
@DynamicUpdate
class CommentEntity(
    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id", insertable = true, updatable = false)
    private val article: ArticleEntity,

    @field:NotBlank
    @Column(length = 100, nullable = false)
    var content: String
) : BaseSeqEntity() {

    override fun toString(): String {
        return super.buildStringHelper()
            .add("article", article)
            .add("content", content)
            .toString()
    }
}
