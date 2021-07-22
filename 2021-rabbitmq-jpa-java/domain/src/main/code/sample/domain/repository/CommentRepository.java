package sample.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.domain.model.ArticleEntity;
import sample.domain.model.CommentEntity;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Optional<CommentEntity> findFirstByArticleOrderByIdDesc(ArticleEntity article);
}
