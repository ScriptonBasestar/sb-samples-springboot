package sample.member.app.service

import org.junit.Assert
import org.junit.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import sample.member.app.web.member.MemberService
import sample.member.domain.model.UserEntity

class MemberServiceTest extends BaseServiceTest<MemberService> {

	@Test
	void 'friend list'() {
		//when
		PageRequest pageRequest = PageRequest.of(0, 10)
		Page<UserEntity> page = service.list(pageRequest)
		//then
		Assert.assertNotNull(page)
		Assert.assertNotNull(page.content)
	}

	@Test
	void 'friend detail'() {
		//when
		UserEntity userEntity = service.detail("username1")
		//then
		Assert.assertNotNull(userEntity)
		Assert.assertEquals("username1", userEntity.username)
	}
}
