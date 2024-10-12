package bingsoochef.bingsoochef.toppping.application.command

import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.ToppingError

const val contentLength = 30

data class RegisterCommentCommand(
    val userId: Long,
    val toppingId: Long,
    val commentContent: String
) {
    init {
        if (commentContent.length > contentLength)
            throw BingsooException(ToppingError.COMMENT_ILLEGAL_REQUEST, "코멘트는 ${contentLength}자 이하여야 합니다.")
    }
}