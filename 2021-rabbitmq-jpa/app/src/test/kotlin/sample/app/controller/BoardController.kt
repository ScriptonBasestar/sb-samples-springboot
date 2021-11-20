package sample.app.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.json.MappingJackson2JsonView

@ExtendWith(MockitoExtension::class)
class BoardController {

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var renewFirstStepService: RenewFirstStepService

    @InjectMocks
    private lateinit var controller: RenewRestController

    companion object {
        private lateinit var objectMapper: ObjectMapper

        @JvmStatic
        @BeforeAll
        fun setupMapper() {
            objectMapper = jacksonObjectMapper()
        }
    }

    @BeforeEach
    fun setupMock() {
        val mappingJackson = MappingJackson2JsonView().apply {
            objectMapper = objectMapper
        }
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setCustomArgumentResolvers(PageableHandlerMethodArgumentResolver())
            .setViewResolvers(ViewResolver { viewName, locale -> mappingJackson })
            .build()
    }

    @Test
    fun renewFailed() {
        val renewFailedMessageDTO = RenewFailedInDTO(
            serviceName = "서비스001",
            subscriptionId = 1L,
            planPrice = 50000,
            planName = "플랜001",
            pgIdkey = "pgidkey001",
            customerId = 3L,
            customerName = "customer001",
            customerPhone = "01000000000",
            customerEmail = "a@a.com",
            clientId = 2L,
            clientName = "client001",
            paymentFailReason = "실패할만해서"
        )
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/renew/failed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(renewFailedMessageDTO))
        ).andDo(
            ResultHandler {
                println(it.request)
                println(it.response)
                println(it.modelAndView)
            }
        )
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)

        Mockito.doNothing().`when`(renewFirstStepService.listenRenewFailed(renewFailedMessageDTO))
        Mockito.verify(renewFirstStepService).listenRenewFailed(renewFailedMessageDTO)
        Mockito.verifyNoInteractions(renewFirstStepService)
    }

    @Test
    fun renewSucceeded() {
        val renewSucceededDTO = RenewSucceededInDTO(
            subscriptionId = 1L,
            pgIdkey = "pgidkey001",
            customerId = 1L,
            clientId = 2L,
        )
//        Mockito.doNothing().doThrow(RuntimeException()).`when`(renewFirstStepService.listenRenewSucceed(renewSucceededDTO)).
        Mockito.doNothing().`when`(renewFirstStepService.listenRenewSucceed(renewSucceededDTO))
        Mockito.verify(renewFirstStepService).listenRenewSucceed(renewSucceededDTO)
//        Mockito.verifyNoInteractions(renewFirstStepService)
    }
}
