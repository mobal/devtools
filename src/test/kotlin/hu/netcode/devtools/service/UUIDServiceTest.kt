package hu.netcode.devtools.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        UUIDService::class
    ]
)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class UUIDServiceTest {
    @Autowired
    private lateinit var uuidService: UUIDService

    @DisplayName(value = "UUIDService: Tests for generate function")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class GetIP {
        @Test
        fun `successfully generate uuid`() {
            val result = uuidService.generate()
            val parts = result.first().split("-")
            assertEquals(5, parts.size)
            assertEquals(8, parts.first().length)
            assertEquals(4, parts[1].length)
            assertEquals(4, parts[2].length)
            assertEquals(4, parts[3].length)
            assertEquals(12, parts[4].length)
        }

        @Test
        fun `successfully generate three uuids`() {
            val resultList = uuidService.generate(3)
            resultList.forEach {
                val parts = it.split("-")
                assertEquals(5, parts.size)
                assertEquals(8, parts.first().length)
                assertEquals(4, parts[1].length)
                assertEquals(4, parts[2].length)
                assertEquals(4, parts[3].length)
                assertEquals(12, parts[4].length)
            }
        }
    }
}
