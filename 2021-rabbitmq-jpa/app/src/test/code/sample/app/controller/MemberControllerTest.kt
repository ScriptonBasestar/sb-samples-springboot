package sample.app.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.json.MappingJackson2JsonView
import sample.app.web.member.MemberRestController
import sample.app.web.member.MemberService
import sample.domain.model.UserEntity

@ExtendWith(MockitoExtension::class)
//@ActiveProfiles("test")
//@SpringBootTest(classes = [MainApplication::class])
//@AutoConfigureWebTestClient
//@AutoConfigureMockMvc
class MemberControllerTest {

    //    @Autowired
//    protected WebApplicationContext wac

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var service: MemberService

    @InjectMocks
    private lateinit var controller: MemberRestController

    //given
    @BeforeEach
    fun setupMock() {
//        MockitoAnnotations.openMocks(this)
        val mappingJackson = MappingJackson2JsonView().apply {
            objectMapper = jacksonObjectMapper()
        }
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setCustomArgumentResolvers(PageableHandlerMethodArgumentResolver())
            .setViewResolvers(ViewResolver { viewName, locale -> mappingJackson })
            .build()
    }

    @Test
    fun `test member list rest controller`() {
        val pageable = Pageable.ofSize(10).withPage(0)
        Mockito.`when`(service.list(pageable)).thenReturn(
            PageImpl<UserEntity>(
                (1..10).map { idx ->
					UserEntity(
						"username$idx",
						"realname$idx",
						"email$idx@email.com",
						"passw0rd$idx"
					)
                },
                pageable,
                10
            )
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/member/rest/list")
                .param("page", pageable.pageNumber.toString()).param("size", pageable.pageSize.toString())
        ).andExpect(MockMvcResultMatchers.status().isOk)

        Mockito.verify(service).list(pageable)
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    fun `test member detail rest controller`() {
        val idx = 1
        val userEntity = UserEntity(
			"username$idx",
			"realname$idx",
			"email$idx@email.com",
			"passw0rd$idx"
		)
        Mockito.`when`(service.detail("username1")).thenReturn(userEntity)
//        Mockito.doReturn(userEntity).`when`(service).detail("username1")
        mockMvc.perform(
            MockMvcRequestBuilders.get("/member/rest/detail")
                .param("username", "username1")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)

        Mockito.verify(service).detail("username1")
        Mockito.verifyNoMoreInteractions(service)
    }

}
