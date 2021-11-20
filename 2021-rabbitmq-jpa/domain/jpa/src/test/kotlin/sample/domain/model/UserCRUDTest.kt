package sample.domain.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional
import sample.domain.DomainTestApplication
import sample.domain.exception.TestFailedException
import sample.domain.jpa.model.user.UserEntity
import sample.domain.jpa.model.user.UserRepository

/**
 * UserEntity CRUD 테스트
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = [DomainTestApplication::class])
@Transactional
class UserCRUDTest {
    @Autowired
    private val userRepository: UserRepository? = null

    // given
    @BeforeAll
    fun insertEntity() {
        for (i in 0..4) {
            val userEntity = UserEntity(
                "username$i",
                "realname$i",
                "email$i@email.com",
                "password0"
            )
            userRepository!!.save(userEntity)
        }
    }

    @Test
    fun passwordBcrypt() {
        val userEntity = userRepository!!.findById(1L).get()
        println("=========================")
        println(userEntity)
    }

    //    @Test(expected = DataIntegrityViolationException.class)
    @Test
    fun insertDuplicateUser() {
        // when
        val userEntity = UserEntity(
            "username0",
            "realname0",
            "email0@email.com",
            "password0"
        )
        Assertions.assertThrows(DataIntegrityViolationException::class.java) { userRepository!!.save(userEntity) }
        // then exception
    }

    // 'select 사용자 목록 출력 및 createdAt 값 확인'() {
    @Test
    fun selectCreatedAt() {
        // when
        val userList = userRepository!!.findAll()
        // then
        for (user in userList) {
            Assertions.assertNotNull(user.username)
            Assertions.assertNotNull(user.createdAt)
        }
    }

    // 'update 닉네임 변경 - 값변경 및 lastUpdatedTime 변경확인'()
    @Test
    fun updatejdjdjdj() {
        // when
        val userOptional = userRepository!!.findById(1L)
        if (userOptional.isEmpty) {
            throw TestFailedException("사용자가 없습니다")
        }
        var user = userOptional.get()
        val date0 = user.updatedAt
        try {
            Thread.sleep(1000L)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        user.nickname = "nicky"
        user = userRepository.saveAndFlush(user)
        val date1 = user.updatedAt

        // then
        println(date0)
        println(date1)
        Assertions.assertTrue(date0.isBefore(date1))
    }

    @Test
    fun //    void 'delete 탈퇴 체크'()
    delete() {

        // when
        userRepository!!.deleteById(1L)
        val userOptional = userRepository.findById(1L)
        // then
        Assertions.assertTrue(userOptional.isEmpty)
    }
}
