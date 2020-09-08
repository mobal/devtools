package hu.netcode.devtools.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class IPService {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getIP(req: HttpServletRequest): String {
        return req.remoteAddr
    }
}
