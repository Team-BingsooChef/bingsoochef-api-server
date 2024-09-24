package bingsoochef.bingsoochef.common.exception

import bingsoochef.bingsoochef.common.exception.code.ErrorCode
import org.springframework.http.HttpStatus

class BingsooException(
    val errorCode: ErrorCode,
    val detail: String? = null
) : RuntimeException(errorCode.message()) {

    fun httpStatus(): HttpStatus {
        return errorCode.httpStatus()
    }

    fun code(): String {
        return errorCode.code()
    }

    fun message(): String {
        return errorCode.message()
    }
}