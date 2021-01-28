package hu.netcode.devtools.controller

import com.ninjasquad.springmockk.MockkBean
import hu.netcode.devtools.service.ExceptionService
import hu.netcode.devtools.service.IPService
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(
    value = [
        ExceptionService::class,
        IPController::class
    ]
)
class IPControllerTest {
    @Autowired
    private lateinit var exceptionService: ExceptionService
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var ipService: IPService

    @DisplayName(value = "IPController: Tests for function get")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class Get {
        @Test
        fun `successful get`() {
            every { ipService.getIP(any()) } returns "127.0.0.1"
            mockMvc.get("/api/ip") {
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }
        }
    }
}
