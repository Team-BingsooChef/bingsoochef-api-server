package bingsoochef.bingsoochef.common.exception

import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BingsooExceptionHandler {

    @ExceptionHandler(BingsooException::class)
    fun handleBingsooException(e: BingsooException): ProblemDetail {

        val problemDetail = ProblemDetail.forStatus(e.httpStatus())
        problemDetail.title = e.message
        problemDetail.detail = e.detail
        
        return problemDetail
    }
}