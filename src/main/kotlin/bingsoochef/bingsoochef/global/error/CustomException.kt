package bingsoochef.bingsoochef.global.error

import org.springframework.http.HttpStatus

open class CustomException : RuntimeException {

    val httpStatus: HttpStatus
    override val message: String
        get() = super.message ?: ""

    constructor(message: String) : super(message) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    }

    constructor(message: String, httpStatus: HttpStatus) : super(message) {
        this.httpStatus = httpStatus
    }

    fun getStatusCode() : Int {
        return httpStatus.value()
    }
}