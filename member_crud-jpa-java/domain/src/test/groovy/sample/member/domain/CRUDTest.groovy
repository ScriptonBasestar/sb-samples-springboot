package sample.member.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import sample.member.domain.model.UserEntity
import sample.member.domain.repository.UserRepository

/**
 * UserEntity CRUD 테스트
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DomainTestApplication.class)
@Transactional
class CRUDTest {

	@Autowired
	private UserRepository userRepository

	//given
	@BeforeAll
	static void 'insert entity'() {
		(1..5).each {
			UserEntity userEntity = new UserEntity(
					username: "username$it",
					realname: "realname$it",
					email: "email$it@email.com"
			)
			userRepository.save(userEntity)
		}
	}

//	@Test(expected = DataIntegrityViolationException.class)
	@Test
	void 'insert 중복사용자 추가'() {
		//when
		UserEntity userEntity = new UserEntity(
				username: "username0",
				realname: "realname0",
				email: "email0@email.com"
		)
		Assertions.assertThrows(DataIntegrityViolationException.class,
				{
					userRepository.save(userEntity)
				})
		//then exception
	}

	@Test
	void 'select 사용자 목록 출력 및 createdAt 값 확인'() {
		//when
		List<UserEntity> userList = userRepository.findAll()
		//then
		userList.each { user ->
			Assertions.assertNotNull(user.username)
			Assertions.assertNotNull(user.createdAt)
		}
	}

	@Test
	void 'update 닉네임 변경 - 값변경 및 lastUpdatedTime 변경확인'() {
		//when
		def userOptional = userRepository.findById(1L)
		if (!userOptional.present) {
			throw new TestFailException("사용자가 없습니다")
		}
		def user = userOptional.get()
		Date date0 = user.lastUpdatedAt
		Thread.sleep(1000L)
		user.nickname = 'nicky'
		user = userRepository.saveAndFlush(user)
		Date date1 = user.lastUpdatedAt

		//then
		println(date0)
		println(date1)
		Assertions.assertTrue(date0.before(date1))
	}

	@Test
	void 'delete 탈퇴 체크'() {
		//when
		userRepository.deleteById(1L)
		def userOptional = userRepository.findById(1L)
		//then
		Assertions.assertTrue(!userOptional.present)
	}

}
