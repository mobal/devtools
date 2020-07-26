package hu.netcode.devtools.controller

import hu.netcode.devtools.service.IPService
import javax.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/api/ip"]
)
class IPController(
    private val ipService: IPService
) {
    @GetMapping
    fun get(req: HttpServletRequest): String {
        return ipService.getIP(req)
    }
}
