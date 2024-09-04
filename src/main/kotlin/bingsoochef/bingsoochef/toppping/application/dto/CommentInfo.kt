package bingsoochef.bingsoochef.toppping.application.dto

import java.time.LocalDateTime

data class CommentInfo (
    val id: Long,
    val content: String,
    val createdTime: LocalDateTime
)