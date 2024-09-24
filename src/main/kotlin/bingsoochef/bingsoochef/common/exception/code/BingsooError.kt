package bingsoochef.bingsoochef.common.exception.code

import org.springframework.http.HttpStatus

enum class BingsooError(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String
) : ErrorCode{

    ILLEGAL_REQUEST(HttpStatus.BAD_REQUEST, "B000", "잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "B100", "존재하지 않는 빙수입니다."),
    DUPLICATE(HttpStatus.CONFLICT, "B200", "이미 빙수가 존재합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "B300", "빙수에 대한 권한이 없습니다.");

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