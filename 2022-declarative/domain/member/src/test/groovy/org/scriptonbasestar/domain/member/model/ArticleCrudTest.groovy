package org.scriptonbasestar.domain.member.model
//package sample.domain.model
//
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInstance
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.transaction.annotation.Transactional
//import sample.domain.DomainTestApplication
//import sample.domain.jpa.model.article.ArticleEntity
//import sample.domain.jpa.model.comment.CommentEntity
//import sample.domain.jpa.model.article.ArticleRepository
//import sample.domain.jpa.model.comment.CommentRepository
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = DomainTestApplication.class)
//@Transactional
//@ActiveProfiles("test")
//class ArticleCrudTest {
//
//    private final ArticleRepository articleRepository
//    private final CommentRepository commentRepository
//
//    @Autowired
//    ArticleCrudTest(
//        ArticleRepository articleRepository,
//        CommentRepository commentRepository
//    ) {
//        this.articleRepository = articleRepository
//        this.commentRepository = commentRepository
//    }
//
//    @BeforeAll
//    void write() {
//        ArticleEntity articleEntity1 = new ArticleEntity(
//            title: "title1",
//            content: "content1"
//        )
//        articleRepository.saveAndFlush(articleEntity1)
//        (1..5).each {
//            CommentEntity commentEntity1_1 = new CommentEntity(
//                article: articleEntity1,
//                content: "content${it}"
//            )
//            commentRepository.save(commentEntity1_1)
//        }
//    }
//
//    @Test
//    void 'before insert success check'() {
//        def article = articleRepository.findAll()[0]
//        def comment = commentRepository.findFirstByArticleOrderByIdDesc(article)
//        Assertions.assertNotNull(article)
//        Assertions.assertNotNull(comment)
//    }
//
//    @Test
//    void 'article에 comment 새끼달아주기'() {
//        //생각해보니 안된다. article null을 허용해주지 않을거니까
//        def comments = new ArrayList<CommentEntity>()
//        (1..10).each {
//            comments.add(new CommentEntity(
////                article: ,
//                content: "한글잘써지나 첷k content${it}"
//            ))
//        }
//        def articleEntity = new ArticleEntity(
//            title: "title12 새끼달기",
//            content: "content2 한글테스트"
//        )
//        articleEntity.comments = comments
//        //org.springframework.dao.DataIntegrityViolationException
//        //org.hibernate.exception.ConstraintViolationException
//        //org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException
//        Assertions.assertThrows(org.springframework.dao.DataIntegrityViolationException.class,
//            () -> {
//                articleRepository.save(articleEntity)
//            })
//    }
//
//    @Test
//    void 'article을 comment에 넣어주기'() {
//        println("이건 beforeall에서 하고 있어서 생략한다")
//    }
//
//}
