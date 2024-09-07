package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Comment
import java.time.LocalDateTime

data class CommentInfo (
    val id: Long,
    val content: String,
    val createdTime: LocalDateTime
) {
    companion object {
        fun from(comment: Comment): CommentInfo {
            return CommentInfo(
                id = comment.id!!,
                content = comment.content,
                createdTime = comment.createdTime
            )
        }
    }
}