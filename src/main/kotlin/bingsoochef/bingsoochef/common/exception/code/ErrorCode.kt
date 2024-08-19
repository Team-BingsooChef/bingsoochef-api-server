package bingsoochef.bingsoochef.common.exception.code

import org.springframework.http.HttpStatus

interface ErrorCode {
    fun httpStatus(): HttpStatus;
    fun code(): String;
    fun message(): String;
}