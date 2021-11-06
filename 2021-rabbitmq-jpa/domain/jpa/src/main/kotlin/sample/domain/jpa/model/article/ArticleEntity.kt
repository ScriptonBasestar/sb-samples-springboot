package sample.domain.jpa.model.article

import org.hibernate.annotations.DynamicUpdate
import sample.domain.jpa.model.BaseEntity
import sample.domain.jpa.model.comment.CommentEntity
import javax.persistence.*

@Entity
@Table(name = "T_ARTICLE", indexes = [Index(name = "IDX__T_ARTICLE__TITLE", columnList = "title", unique = false)])
@DynamicUpdate
class ArticleEntity(
    @field:Column(
        length = 100,
        nullable = false
    )
    val title: String,
    @field:Column(
        length = 1000,
        nullable = false,
        columnDefinition = "mediumtext"
    )
    private val content: String
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val comments: MutableList<CommentEntity> = mutableListOf()
    override fun toString(): String {
        return super.buildStringHelper()
            .add("title", title)
            .add("content", content)
            .toString()
    }
}
