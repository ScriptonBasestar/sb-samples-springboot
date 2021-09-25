package sample.domain.model;//package sample.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import sample.domain.DomainTestApplication;
import sample.domain.exception.TestFailedException;
import sample.domain.jpa.model.UserEntity;
import sample.domain.jpa.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * UserEntity CRUD 테스트
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = DomainTestApplication.class)
@Transactional
public class UserCRUDTest {

    @Autowired
    private UserRepository userRepository;

    //given
    @BeforeAll
    void insertEntity() {
        for (int i = 0; i < 5; i++) {
            UserEntity userEntity = new UserEntity(
                "username" + i,
                "realname" + i,
                "email" + i + "@email.com",
                "password0"
            );
            userRepository.save(userEntity);
        }
    }

    @Test
    void passwordBcrypt() {
        UserEntity userEntity = userRepository.findById(1L).get();
        System.out.println("=========================");
        System.out.println(userEntity);
    }

    //    @Test(expected = DataIntegrityViolationException.class)
    @Test
    void insertDuplicateUser() {
        //when
        UserEntity userEntity = new UserEntity(
            "username0",
            "realname0",
            "email0@email.com",
            "password0"
        );
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(userEntity);
        });
        //then exception
    }

    //'select 사용자 목록 출력 및 createdAt 값 확인'() {
    @Test
    void selectCreatedAt() {
        //when
        List<UserEntity> userList = userRepository.findAll();
        //then
        for (UserEntity user : userList) {
            Assertions.assertNotNull(user.getUsername());
            Assertions.assertNotNull(user.getCreatedAt());
        }
    }

    //'update 닉네임 변경 - 값변경 및 lastUpdatedTime 변경확인'()
    @Test
    void updatejdjdjdj() {
        //when
        Optional<UserEntity> userOptional = userRepository.findById(1L);
        if (userOptional.isEmpty()) {
            throw new TestFailedException("사용자가 없습니다");
        }
        UserEntity user = userOptional.get();
        LocalDateTime date0 = user.getUpdatedAt();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.setNickname("nicky");
        user = userRepository.saveAndFlush(user);
        LocalDateTime date1 = user.getUpdatedAt();

        //then
        System.out.println(date0);
        System.out.println(date1);
        Assertions.assertTrue(date0.isBefore(date1));
    }

    @Test
//    void 'delete 탈퇴 체크'()
    void delete() {

        //when
        userRepository.deleteById(1L);
        Optional<UserEntity> userOptional = userRepository.findById(1L);
        //then
        Assertions.assertTrue(userOptional.isEmpty());
    }

}
