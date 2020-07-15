package hu.netcode.devtools.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/"])
class WelcomeController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun welcome(): String {
        return "hello, world!"
    }
}
