package hu.netcode.devtools.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.servlet.http.HttpServletRequest

@SpringBootTest(
    classes = [
        IPService::class
    ]
)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class IPServiceTest {
    private companion object {
        const val LOCALHOST = "127.0.0.1"
    }
    @Autowired
    private lateinit var ipService: IPService

    @DisplayName(value = "IPService: Test for create response map function")
    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    inner class GetIP {
        @Test
        fun `successfully get ip`() {
            val httpServletRequestMock = mockk<HttpServletRequest>(relaxed = true)
            every { httpServletRequestMock.remoteAddr } returns LOCALHOST
            val result = ipService.getIP(httpServletRequestMock)
            assertEquals(LOCALHOST, result)
        }
    }
}
