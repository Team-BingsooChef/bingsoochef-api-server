package bingsoochef.bingsoochef.toppping.presentation.req

data class TryQuizRequest(
    val userId: Long,    // 로그인 구현 전까지 사용할 임시 필드
    val quizId: Long,
    val questionId: Long
)
