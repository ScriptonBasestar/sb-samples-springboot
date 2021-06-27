package sample.app.controller

import groovy.json.JsonOutput
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import sample.app.MainApplication
import sample.app.web.signup.SignupRequestDto
import sample.app.web.signup.SignupRestController
import sample.app.web.signup.SignupService

@SpringBootTest(classes = MainApplication.class)
class SignupControllerTest {

//    @Autowired
//    protected WebApplicationContext wac
    protected MockMvc mockMvc

    @InjectMocks
    private SignupRestController controller
    @Mock
    private SignupService service

    //given
    @BeforeEach
    void before() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    void 'username exists 체크 - true 중복있음 404 사용불가'() throws Exception {
        Mockito.when(service.isExistsUsername("username1")).thenReturn(true)
        mockMvc.perform(MockMvcRequestBuilders.get("/signup/rest/checkUsername")
            .param("username", "username1"))
            .andExpect(MockMvcResultMatchers.status().is(404))

        Mockito.verify(service).isExistsUsername("username1")
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    void 'username exists 체크 - false 중복없음 200 사용가능'() throws Exception {
        Mockito.when(service.isExistsUsername("username1")).thenReturn(false)
        mockMvc.perform(MockMvcRequestBuilders.get("/signup/rest/checkUsername")
            .param("username", "username1"))
            .andExpect(MockMvcResultMatchers.status().isOk())

        Mockito.verify(service).isExistsUsername("username1")
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    void 'email exists 체크 - true 중복있음 404 사용불가'() throws Exception {
        Mockito.when(service.isExistsEmail("email1@email.com")).thenReturn(true)
        mockMvc.perform(MockMvcRequestBuilders.get("/signup/rest/checkEmail")
            .param("email", "email1@email.com"))
            .andExpect(MockMvcResultMatchers.status().is(404))

        Mockito.verify(service).isExistsEmail("email1@email.com")
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    void 'email exists 체크 - false 중복없음 200 사용가능'() throws Exception {
        Mockito.when(service.isExistsEmail("email1@email.com")).thenReturn(false)
        mockMvc.perform(MockMvcRequestBuilders.get("/signup/rest/checkEmail")
            .param("email", "email1@email.com"))
            .andExpect(MockMvcResultMatchers.status().isOk())

        Mockito.verify(service).isExistsEmail("email1@email.com")
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    void 'test signup rest controller - 정상동작'() throws Exception {
        def signupRequest = new SignupRequestDto(
            username: "user100",
            realname: "real100",
            email: "email100@email.com",
            nickname: "nickname100",
            password: "password100",
            passwordConfirm: "password100"
        )
        Mockito.doNothing().when(service).signup(signupRequest)
        mockMvc.perform(MockMvcRequestBuilders.post("/signup/rest")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonOutput.toJson(signupRequest)))
            .andExpect(MockMvcResultMatchers.status().isOk())

        Mockito.verify(service).signup(signupRequest)
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    void 'test signup rest controller - 비밀번호 validation 동작확인'() throws Exception {
        def signupRequest = new SignupRequestDto(
            username: "user100",
            realname: "real100",
            email: "email100@email.com",
            nickname: "nickname100",
            password: "password100",
            passwordConfirm: "xxxxxx"
        )
        mockMvc.perform(MockMvcRequestBuilders.post("/signup/rest")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonOutput.toJson(signupRequest)))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError())
    }

}
