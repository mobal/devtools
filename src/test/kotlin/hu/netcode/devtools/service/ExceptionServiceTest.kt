package hu.netcode.devtools.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(
    classes = [
        ExceptionService::class
    ]
)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ExceptionServiceTest {
    private companion object {
        const val MESSAGE = "Message"
    }
    @Autowired
    private lateinit var exceptionService: ExceptionService

    @DisplayName(value = "ExceptionService: Tests for create response map function")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class CreateResponseMap {
        @Test
        fun `successfully create response map`() {
            val ex = Exception(MESSAGE)
            val result = exceptionService.createResponseMap(ex, HttpStatus.NOT_FOUND)
            assertTrue(result.isNotEmpty())
            assertEquals(HttpStatus.NOT_FOUND.value(), result["code"])
            assertEquals(MESSAGE, result["msg"])
        }
    }
}
