package bingsoochef.bingsoochef.global.error

import org.springframework.http.HttpStatus

class ForbiddenException(message: String) : CustomException(message, HttpStatus.FORBIDDEN)