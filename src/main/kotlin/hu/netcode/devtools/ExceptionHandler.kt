package hu.netcode.devtools

import hu.netcode.devtools.service.ExceptionService
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler(
    private val exceptionService: ExceptionService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(req: HttpServletRequest, ex: Exception): Map<String, Any> {
        return exceptionService.createResponseMap(ex, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(req: HttpServletRequest, ex: Exception): Map<String, Any> {
        return exceptionService.createResponseMap(ex, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
