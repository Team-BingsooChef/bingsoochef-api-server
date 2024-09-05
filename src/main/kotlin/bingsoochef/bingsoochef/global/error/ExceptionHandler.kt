package bingsoochef.bingsoochef.global.error

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message),HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(e.getStatusCode(), e.message),e.httpStatus)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(e.getStatusCode(), e.message),e.httpStatus)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val message: String = when (val cause = e.cause) {
            is JsonParseException -> "올바른 JSON 형식이 아닙니다."

            is JsonMappingException -> {
                val path = cause.path.joinToString("/") { it.fieldName }
                when (cause) {
                    is InvalidFormatException -> {
                        when {
                            cause.targetType.isEnum ->
                                "$path 은 Enum으로, ${cause.targetType.enumConstants.joinToString(", ") { (it as Enum<*>).name }} 중 하나여야 합니다."
                            else -> "$path 의 타입은 ${cause.targetType.simpleName}이어야 합니다."
                        }
                    }
                    is MismatchedInputException -> {
                        if (cause.targetType != null)
                            "${path}을/를 ${cause.targetType.simpleName}에 매핑할 수 없습니다."
                        else
                            "${path}을/를 매핑하는 데 실패하였습니다."
                    }

                    else -> "JSON 매핑에 실패하였습니다."
                }
            }

            else -> "HTTP 메시지를 읽는 중 알 수 없는 오류가 발생하였습니다."
        }

        return ResponseEntity<ErrorResponse>(
            ErrorResponse(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ServletRequestBindingException::class)
    fun handleServletRequestBindingException(e : ServletRequestBindingException) : ResponseEntity<ErrorResponse> {

        val message : String = when(e) {
                is MissingServletRequestParameterException -> "Request Parameter에 ${e.parameterName}이 누락되었습니다."

                is MissingPathVariableException -> "Path Variable에 ${e.variableName}이(가) 누락되었습니다."

                is MissingRequestHeaderException -> "Request Header에 ${e.headerName}이(가) 누락되었습니다."

                is MissingRequestCookieException -> "Request Cookie에 ${e.cookieName}이(가) 누락되었습니다."

                is MissingMatrixVariableException -> "Matrix Variable에 ${e.variableName}이(가) 누락되어 있습니다."

                else -> "HTTP 메시지를 ServletRequest에 바인딩하던 중 오류가 발생하였습니다."
            }

        return ResponseEntity<ErrorResponse>(
            ErrorResponse(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST)
    }
}