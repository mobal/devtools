package hu.netcode.devtools.exception

import hu.netcode.devtools.service.ExceptionService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException

@RestControllerAdvice
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ExceptionHandler(
    private val exceptionService: ExceptionService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [ConstraintViolationException::class])
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(req: HttpServletRequest, ex: Exception): Map<String, Any> {
        return exceptionService.createResponseMap(ex, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(req: HttpServletRequest, ex: Exception): Map<String, Any> {
        return exceptionService.createResponseMap(ex, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
