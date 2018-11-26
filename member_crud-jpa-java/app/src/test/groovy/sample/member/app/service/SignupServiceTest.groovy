package sample.member.app.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import sample.member.app.web.signup.SignupRequestDto
import sample.member.app.web.signup.SignupService
import sample.member.core.exception.BusinessException

class SignupServiceTest extends BaseServiceTest<SignupService> {

	@Test
	void 'check exists username'() {
		//when
		def isExists = service.isExistsUsername("username1")
		//then
		Assertions.assertTrue(isExists)
	}

	@Test
	void 'check exists email'() {
		//when
		def isExists = service.isExistsEmail("email1@email.com")
		//then
		Assertions.assertTrue(isExists)
	}

//	@Test(expected = DataIntegrityViolationException.class)
//	@Test(expected = BusinessException.class)
	@Test
	void 'exists user'() {
		//when
		Assertions.assertThrows(BusinessException.class,
				{
					def idx = 1
					service.signup(
							new SignupRequestDto(
									username: "username$idx",
									realname: "realname$idx",
									email: "email$idx@email.com",
									nickname: "nickname$idx",
									password: "passw0rd$idx",
									passwordConfirm: "passw0rd$idx"
							))
				})
		//then exception
	}

}
