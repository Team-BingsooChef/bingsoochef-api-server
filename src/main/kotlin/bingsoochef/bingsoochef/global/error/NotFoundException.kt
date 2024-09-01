package bingsoochef.bingsoochef.global.error

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : CustomException(message, HttpStatus.NOT_FOUND) {
}