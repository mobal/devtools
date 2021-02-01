package hu.netcode.devtools.controller

import hu.netcode.devtools.service.MongoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/api/mongo"]
)
@Validated
class MongoController(
    private val mongoService: MongoService
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
                description = "Validation failed",
                responseCode = "400"
            )
        ]
    )
    @GetMapping(value = ["/objectId"])
    @Operation(summary = "Generate a fixed number of object ids between 1 and 9999")
    @ResponseStatus(value = HttpStatus.OK)
    fun objectId(
        @Max(value = 9999)
        @Min(value = 1)
        @RequestParam(defaultValue = "1", required = false)
        size: Int
    ): List<String> {
        return mongoService.generateObjectId(size)
    }
}
