package hu.netcode.devtools.controller

import com.ninjasquad.springmockk.MockkBean
import hu.netcode.devtools.service.ExceptionService
import hu.netcode.devtools.service.UUIDService
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
        UUIDController::class
    ]
)
class UUIDControllerTest {
    private companion object {
        const val URL = "/api/uuid"
    }

    @Autowired
    private lateinit var exceptionService: ExceptionService
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var uuidService: UUIDService

    @DisplayName(value = "UUIDController: Tests for function get")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class Get {
        @Test
        fun `successful get uuid`() {
            every { uuidService.generate(any()) } returns listOf("91dce877-c9ed-423b-85f4-ecd09495ba36")
            mockMvc.get(URL) {
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }
        }

        @Test
        fun `size parameter validation failed`() {
            mockMvc.get("$URL?size=10000") {
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }
    }
}
