package bingsoochef.bingsoochef.common.exception.code

import org.springframework.http.HttpStatus

enum class AuthError(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String
) : ErrorCode {
    EMAIL_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "A001", "잘못된 이메일 형식"),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "A002", "메일 발송 실패"),
    EMAIL_CERTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "A003", "인증 정보를 찾을 수 없음"),
    EMAIL_CERTIFICATION_FAILED(HttpStatus.BAD_REQUEST, "A004", "인증 실패, 인증을 다시 요청해주세요");

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