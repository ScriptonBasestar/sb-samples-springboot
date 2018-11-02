package sample.member.app.service

import org.junit.Assert
import org.junit.Test
import sample.member.app.web.signup.SignupRequestDto
import sample.member.app.web.signup.SignupService
import sample.member.core.exception.BusinessException

class SignupServiceTest extends BaseServiceTest<SignupService> {

	@Test
	void 'check exists username'() {
		//when
		def isExists = service.isExistsUsername("username1")
		//then
		Assert.assertTrue(isExists)
	}

	@Test
	void 'check exists email'() {
		//when
		def isExists = service.isExistsEmail("email1@email.com")
		//then
		Assert.assertTrue(isExists)
	}

//	@Test(expected = DataIntegrityViolationException.class)
	@Test(expected = BusinessException.class)
	void 'exists user'() {
		//when
		def it = 1
		service.signup(
				new SignupRequestDto(
						username: "username$it",
						realname: "realname$it",
						email: "email$it@email.com",
						nickname: "nickname$it",
						password: "passw0rd$it",
						passwordConfirm: "passw0rd$it"
				))
		//then exception
	}

}
