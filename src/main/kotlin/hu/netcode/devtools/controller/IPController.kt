package hu.netcode.devtools.controller

import hu.netcode.devtools.service.IPService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/api/ip"]
)
class IPController(
    private val ipService: IPService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ApiResponses(
        value = [
            ApiResponse(
                content = [
                    Content(mediaType = "application/json")
                ],
                responseCode = "200"
            ),
            ApiResponse(
                content = [
                    Content(mediaType = "application/json")
                ],
                description = "Internal server error",
                responseCode = "500"
            )
        ]
    )
    @GetMapping
    @Operation(summary = "Return the requesters IP address")
    @ResponseStatus(value = HttpStatus.OK)
    fun get(req: HttpServletRequest): Map<String, String> {
        return mapOf("ip" to ipService.getIP(req))
    }
}
