package sample.domain.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(
    name = "T_ARTICLE",
    indexes = {
        @Index(name = "IDX__T_ARTICLE__TITLE", columnList = "title", unique = false),
    }
)
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArticleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ArticleEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Column(length = 10, nullable = false)
    private String title;

    @Column(length = 10, nullable = false, columnDefinition = "mediumtext")
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<CommentEntity> comments = Collections.EMPTY_LIST;

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
            .add("title", title)
            .add("content", content)
            .toString();
    }
}
