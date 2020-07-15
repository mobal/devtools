package hu.netcode.devtools.controller

import hu.netcode.devtools.service.UUIDService
import javax.servlet.http.HttpServletRequest
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/api/uuid"]
)
@Validated
class UUIDController(
    private val uuidService: UUIDService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun get(
        req: HttpServletRequest,
        @Max(value = 9999)
        @Min(value = 1)
        @RequestParam(defaultValue = "1", required = false)
        size: Int
    ): List<String> {
        return uuidService.generate(size)
    }
}
