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
        MongoService::class
    ]
)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class MongoServiceTest {
    @Autowired
    private lateinit var mongoService: MongoService

    @DisplayName(value = "MongoService: Tests for generateObjectId function")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class GenerateObjectId {
        @Test
        fun `successfully generate objectId`() {
            val result = mongoService.generateObjectId()
            assertEquals(24, result.first().length)
        }

        @Test
        fun `successfully generate three objectIds`() {
            val resultList = mongoService.generateObjectId(3)
            resultList.forEach {
                assertEquals(24, it.length)
            }
        }
    }
}
