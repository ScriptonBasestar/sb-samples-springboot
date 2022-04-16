package org.scriptonbasestar.domain.board.persistence

import org.hibernate.annotations.DynamicUpdate
import org.scriptonbasestar.base.BaseSeqUuidEntity
import javax.persistence.*
import javax.validation.constraints.NotBlank

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
) : BaseSeqUuidEntity() {

    override fun toString(): String {
        return super.buildStringHelper()
            .add("article", article)
            .add("content", content)
            .toString()
    }
}
