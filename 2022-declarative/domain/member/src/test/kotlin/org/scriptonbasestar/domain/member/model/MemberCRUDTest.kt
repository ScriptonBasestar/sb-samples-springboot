package org.scriptonbasestar.domain.member.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional
import org.scriptonbasestar.domain.member.DomainTestApplication
import org.scriptonbasestar.domain.member.exception.TestFailedException
import org.scriptonbasestar.domain.member.persistence.MemberEntity
import org.scriptonbasestar.domain.member.persistence.MemberEntityRepository

/**
 * UserEntity CRUD 테스트
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = [DomainTestApplication::class])
@Transactional
class MemberCRUDTest {
    @Autowired
    private lateinit var memberEntityRepository: MemberEntityRepository

    // given
    @BeforeAll
    fun insertEntity() {
        for (i in 0..4) {
            val userEntity = MemberEntity(
                "username$i",
                "realname$i",
                "email$i@email.com",
                "password0"
            )
            memberEntityRepository!!.save(userEntity)
        }
    }

    @Test
    fun passwordBcrypt() {
        val userEntity = memberEntityRepository!!.findById(1L).get()
        println("=========================")
        println(userEntity)
    }

    //    @Test(expected = DataIntegrityViolationException.class)
    @Test
    fun insertDuplicateUser() {
        // when
        val userEntity = MemberEntity(
            "username0",
            "realname0",
            "email0@email.com",
            "password0"
        )
        Assertions.assertThrows(DataIntegrityViolationException::class.java) { memberEntityRepository!!.save(userEntity) }
        // then exception
    }

    // 'select 사용자 목록 출력 및 createdAt 값 확인'() {
    @Test
    fun selectCreatedAt() {
        // when
        val userList = memberEntityRepository!!.findAll()
        // then
        for (user in userList) {
            Assertions.assertNotNull(user.username)
            Assertions.assertNotNull(user.dateAt.createdAt)
        }
    }

    // 'update 닉네임 변경 - 값변경 및 lastUpdatedTime 변경확인'()
    @Test
    fun updatejdjdjdj() {
        // when
        val userOptional = memberEntityRepository!!.findById(1L)
        if (userOptional.isEmpty) {
            throw TestFailedException("사용자가 없습니다")
        }
        var user = userOptional.get()
        val date0 = user.dateAt.updatedAt
        try {
            Thread.sleep(1000L)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        user.nickname = "nicky"
        user = memberEntityRepository.saveAndFlush(user)
        val date1 = user.dateAt.updatedAt

        // then
        println(date0)
        println(date1)
        Assertions.assertTrue(date0.isBefore(date1))
    }

    @Test
    //    void 'delete 탈퇴 체크'()
    fun delete() {
        // when
        memberEntityRepository!!.deleteById(1L)
        val userOptional = memberEntityRepository.findById(1L)
        // then
        Assertions.assertTrue(userOptional.isEmpty)
    }
}
