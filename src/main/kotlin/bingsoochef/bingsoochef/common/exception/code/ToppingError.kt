package bingsoochef.bingsoochef.common.exception.code

import org.springframework.http.HttpStatus

enum class ToppingError(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String
) : ErrorCode {

    ILLEGAL_REQUEST(HttpStatus.BAD_REQUEST, "T000", "잘못된 요청입니다."),
    TOPPING_ILLEGAL_REQUEST(HttpStatus.BAD_REQUEST, "T001", "잘못된 토핑 요청입니다."),
    COMMENT_ILLEGAL_REQUEST(HttpStatus.BAD_REQUEST, "T002", "잘못된 코멘트 요청입니다."),
    QUIZ_ILLEGAL_REQUEST(HttpStatus.BAD_REQUEST, "T003", "잘못된 퀴즈 요청입니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND, "T100", "존재하지 않는 자원입니다."),
    TOPPING_NOT_FOUND(HttpStatus.NOT_FOUND, "T101", "존재하지 않는 토핑입니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "T101", "존재하지 않는 토핑입니다."),
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "T101", "존재하지 않는 퀴즈입니다."),

    DUPLICATE(HttpStatus.CONFLICT, "T200", "이미 존재하는 자원입니다."),
    TOPPING_DUPLICATE(HttpStatus.CONFLICT, "T201", "이미 토핑이 존재합니다."),
    COMMENT_DUPLICATE(HttpStatus.CONFLICT, "T202", "이미 코멘트가 존재합니다."),
    QUIZ_DUPLICATE(HttpStatus.CONFLICT, "T203", "이미 푼 퀴즈입니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN, "T300", "권한이 없습니다."),
    TOPPING_FORBIDDEN(HttpStatus.FORBIDDEN, "T301", "토핑에 대한 권한이 없습니다."),
    COMMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "T302", "코멘트에 대한 권한이 없습니다."),
    QUIZ_FORBIDDEN(HttpStatus.FORBIDDEN, "T303", "퀴즈에 대한 권한이 없습니다."),
    TOPPING_UNFROZEN(HttpStatus.FORBIDDEN, "T304", "요청한 토핑이 아직 녹지 않았습니다.");

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
