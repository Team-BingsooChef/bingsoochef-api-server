package bingsoochef.bingsoochef.common.exception.code

import org.springframework.http.HttpStatus

enum class AuthError(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String
) : ErrorCode {
    EMAIL_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "A001", "잘못된 이메일 형식"),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "A002", "메일 발송 실패");

    override fun httpStatus(): HttpStatus {
        return httpStatus
    }

    override fun code(): String {
        return code
    }

    override fun message(): String {
        return message
    }
}