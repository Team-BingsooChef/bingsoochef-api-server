package bingsoochef.bingsoochef.global.error

import org.springframework.http.HttpStatus

class DuplicateException(message: String) : CustomException(message, HttpStatus.BAD_REQUEST)  {
}