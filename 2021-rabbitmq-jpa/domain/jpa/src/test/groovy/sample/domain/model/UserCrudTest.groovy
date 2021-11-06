//package sample.domain.model
//
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInstance
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.dao.DataIntegrityViolationException
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.transaction.annotation.Transactional
//import sample.domain.DomainTestApplication
//import sample.domain.exception.TestFailedException
//import sample.domain.jpa.model.user.UserEntity
//import sample.domain.jpa.repository.UserRepository
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = DomainTestApplication.class)
//@Transactional
//@ActiveProfiles("test")
//class UserCrudTest {
//
//    @Autowired
//    private final UserRepository userRepository
//
//    @Autowired
//    UserCrudTest(
//        UserRepository userRepository
//    ) {
//        this.userRepository = userRepository
//    }
//
//    //given
//    @BeforeAll
//    void 'insert entity'() {
//        (0..5).each {
////            UserEntity userEntity = new UserEntity(
////                username: "username$it",
////                realname: "realname$it",
////                email: "email$it@email.com",
////                password: "password0"
////            )
//            UserEntity userEntity = new UserEntity(
//                "username$it",
//                "realname$it",
//                "email$it@email.com",
//                "password0"
//            )
//            userRepository.save(userEntity)
//        }
//    }
//
//    @Test
//    void 'password bcrypt'() {
//        UserEntity userEntity = userRepository.findById(1L).get()
//        println("=========================")
//        println(userEntity)
//    }
//
////    @Test(expected = DataIntegrityViolationException.class)
//    @Test
//    void 'insert 중복사용자 추가'() {
//        //when
//        UserEntity userEntity = new UserEntity(
//            "username0",
//            "realname0",
//            "email0@email.com",
//            "passw0rd",
//        )
//        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
//            userRepository.save(userEntity)
//        })
//        //then exception
//    }
//
//    @Test
//    void 'select 사용자 목록 출력 및 createdAt 값 확인'() {
//        //when
//        List<UserEntity> userList = userRepository.findAll()
//        //then
//        userList.each { user ->
//            Assertions.assertNotNull(user.username)
//            Assertions.assertNotNull(user.createdAt)
//        }
//    }
//
//    @Test
//    void 'update 닉네임 변경 - 값변경 및 lastUpdatedTime 변경확인'() {
//        //when
//        def userOptional = userRepository.findById(1L)
//        if (!userOptional.present) {
//            throw new TestFailedException("사용자가 없습니다")
//        }
//        def user = userOptional.get()
//        Date date0 = user.lastUpdatedAt
//        Thread.sleep(1000L)
//        user.nickname = 'nicky'
//        user = userRepository.saveAndFlush(user)
//        Date date1 = user.lastUpdatedAt
//
//        //then
//        println(date0)
//        println(date1)
//        Assertions.assertTrue(date0.before(date1))
//    }
//
//    @Test
//    void 'delete 탈퇴 체크'() {
//        //when
//        userRepository.deleteById(1L)
//        def userOptional = userRepository.findById(1L)
//        //then
//        Assertions.assertTrue(!userOptional.present)
//    }
//
//}
