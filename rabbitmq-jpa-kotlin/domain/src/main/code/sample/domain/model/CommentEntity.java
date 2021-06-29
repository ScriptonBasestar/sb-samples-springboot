package sample.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public CommentEntity(String content) {
        this.content = content;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id", insertable = true, updatable = false)
    private ArticleEntity article;

    @Column(length = 10, nullable = false)
    private String content;

//    @Column(nullable = false, insertable = true, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column(insertable = true, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    @CreatedDate
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return super.buildStringHelper()
            .add("article", article)
            .add("content", content)
            .toString();
    }
}
