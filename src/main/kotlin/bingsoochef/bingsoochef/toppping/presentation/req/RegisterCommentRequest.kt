package bingsoochef.bingsoochef.toppping.presentation.req

data class RegisterCommentRequest(
    val toppingId: Long,
    val comment: CommentDto
)
