package sample.member.app.controller

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.json.MappingJackson2JsonView
import sample.member.app.MainApplication
import sample.member.app.web.member.MemberRestController
import sample.member.app.web.member.MemberService
import sample.member.domain.model.UserEntity

import java.util.stream.Collectors

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainApplication.class)
class MemberControllerTest {

//	@Autowired
//	protected WebApplicationContext wac
	protected MockMvc mockMvc

	@InjectMocks
	private MemberRestController controller
	@Mock
	private MemberService service

	//given
	@BeforeEach
	void setupMock() {
		MockitoAnnotations.initMocks(this)
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((ViewResolver) { viewName, locale -> return new MappingJackson2JsonView() })
				.build()
	}

	@Test
	void 'test friend list rest controller'() throws Exception {
		def pageable = PageRequest.of(0, 10)
		Mockito.when(service.list(pageable)).thenReturn(new PageImpl<>(
				(1..10).stream().map({
					return new UserEntity(
							username: "username$it",
							realname: "realname$it",
							email: "email$it@email.com",
							password: "passw0rd$it"
					)
				}).collect(Collectors.toList()),
				pageable,
				10
		))
		mockMvc.perform(MockMvcRequestBuilders.get("/member/rest/list")
				.param("page", "0").param("size", "10"))
				.andExpect(MockMvcResultMatchers.status().isOk())

		Mockito.verify(service).list(pageable)
		Mockito.verifyNoMoreInteractions(service)
	}

	@Test
	void 'test friend detail rest controller'() throws Exception {
//		Mockito.doReturn(UserEntity.class).when(service).detail("username1")
//		Mockito.when(service.detail("username1")).thenReturn(Mockito.mock(UserEntity.class))
		Mockito.when(service.detail("username1")).thenReturn(UserEntity.newInstance())
		mockMvc.perform(MockMvcRequestBuilders.get("/member/rest/detail")
				.param("username", "username1"))
				.andExpect(MockMvcResultMatchers.status().isOk())

		Mockito.verify(service).detail("username1")
		Mockito.verifyNoMoreInteractions(service)
	}

}
