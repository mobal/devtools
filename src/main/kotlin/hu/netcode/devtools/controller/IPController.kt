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
@RequestMapping(value = ["/api/ip"])
class IPController(
    private val ipService: IPService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ApiResponses(
        value = [
            ApiResponse(
                content = [
                    Content(mediaType = "text/plain")
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
    @GetMapping(produces = [MediaType.TEXT_PLAIN_VALUE])
    @Operation(summary = "Generate a fixed number of object ids between 1 and 9999")
    @ResponseStatus(value = HttpStatus.OK)
    fun get(req: HttpServletRequest): String {
        return ipService.getIP(req)
    }
}
