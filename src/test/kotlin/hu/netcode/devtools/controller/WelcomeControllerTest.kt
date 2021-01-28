package hu.netcode.devtools.controller

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
        WelcomeController::class
    ]
)
class WelcomeControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @DisplayName(value = "WelcomeController: Tests for function welcome")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class Welcome() {
        @Test
        fun `successfully get welcome page`() {
            mockMvc.get("/") {
                accept = MediaType.TEXT_PLAIN
            }.andExpect {
                status { isOk() }
            }
        }
    }
}
