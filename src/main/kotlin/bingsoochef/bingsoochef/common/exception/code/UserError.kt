package bingsoochef.bingsoochef.common.exception.code

import org.springframework.http.HttpStatus

enum class UserError(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String
) : ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U101", "존재하지 않는 사용자입니다.");

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