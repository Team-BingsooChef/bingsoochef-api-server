package bingsoochef.bingsoochef.toppping.res

import java.time.LocalDateTime

data class CommentDto(
    val commentId: Long,
    val commentContent: String,
    val createdTime: LocalDateTime
)
