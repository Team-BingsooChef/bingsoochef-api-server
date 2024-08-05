package bingsoochef.bingsoochef.toppping.req

data class RegisterCommentRequest(
    val toppingId: Long,
    val comment: CommentDto
)
