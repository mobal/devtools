package hu.netcode.devtools.controller

import com.ninjasquad.springmockk.MockkBean
import hu.netcode.devtools.service.ExceptionService
import hu.netcode.devtools.service.MongoService
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
        MongoController::class
    ]
)
class MongoControllerTest {
    private companion object {
        const val URL = "/api/mongo"
    }

    @Autowired
    private lateinit var exceptionService: ExceptionService
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var mongoService: MongoService

    @DisplayName(value = "MongoController: Tests for function objectId")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class ObjectID {
        @Test
        fun `successful get object id`() {
            every { mongoService.generateObjectId(any()) } returns listOf("601860064e670c003857e579")
            mockMvc.get("$URL/objectId") {
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }
        }

        @Test
        fun `size parameter validation failed`() {
            mockMvc.get("$URL/objectId?size=10000") {
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }
    }
}
