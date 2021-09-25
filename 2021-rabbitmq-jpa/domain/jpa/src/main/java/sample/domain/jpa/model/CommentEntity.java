package sample.domain.jpa.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "T_COMMENT")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CommentEntity(ArticleEntity article, String content) {
        this.content = content;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id", insertable = true, updatable = false)
    private ArticleEntity article;

    @Setter
    @Column(length = 100, nullable = false)
    private String content;

    @Override
    public String toString() {
        return super.buildStringHelper()
            .add("article", article)
            .add("content", content)
            .toString();
    }
}
