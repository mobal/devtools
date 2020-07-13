package hu.netcode.devtools.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ExceptionService {
    fun createResponseMap(ex: Exception, status: HttpStatus): Map<String, Any> {
        return mapOf("code" to status.value(), "msg" to ex.message!!)
    }
}
