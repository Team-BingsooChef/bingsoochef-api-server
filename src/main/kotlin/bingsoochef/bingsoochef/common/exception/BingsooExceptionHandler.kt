package bingsoochef.bingsoochef.common.exception

import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BingsooExceptionHandler {

    @ExceptionHandler(BingsooException::class)
    fun handleBingsooException(e: BingsooException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(e.httpStatus(), e.message())
        
        return problemDetail
    }
}