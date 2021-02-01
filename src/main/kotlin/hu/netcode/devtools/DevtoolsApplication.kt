package hu.netcode.devtools

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class DevtoolsApplication

fun main(args: Array<String>) {
    runApplication<DevtoolsApplication>(*args)
}
