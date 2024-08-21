package bingsoochef.bingsoochef.toppping.presentation.res

import java.time.LocalDateTime

data class CommentDto(
    val commentId: Long,
    val commentContent: String,
    val commentCreatedTime: LocalDateTime
)
