package sample.app.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import sample.app.web.member.MemberService
import sample.domain.model.UserEntity

class MemberServiceTest extends BaseServiceTest<MemberService> {

	@Test
	void 'friend list'() {
		//when
		PageRequest pageRequest = PageRequest.of(0, 10)
		Page<UserEntity> page = service.list(pageRequest)
		//then
        Assertions.assertNotNull(page)
        Assertions.assertNotNull(page.content)
	}

	@Test
	void 'friend detail'() {
		//when
		UserEntity userEntity = service.detail("username1")
		//then
        Assertions.assertNotNull(userEntity)
        Assertions.assertEquals("username1", userEntity.username)
	}
}
