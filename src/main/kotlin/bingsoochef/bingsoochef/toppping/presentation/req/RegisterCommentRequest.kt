package bingsoochef.bingsoochef.toppping.presentation.req

data class RegisterCommentRequest(
    val userId: Long,    // 로그인 구현 전까지 사용할 임시 필드
    val toppingId: Long,
    val comment: CommentDto
) {
    class CommentDto(
        val commentContent: String
    )
}
