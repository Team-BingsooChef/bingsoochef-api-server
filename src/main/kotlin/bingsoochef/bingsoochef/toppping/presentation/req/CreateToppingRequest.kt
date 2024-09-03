package bingsoochef.bingsoochef.toppping.presentation.req

data class CreateToppingRequest (
    val userId: Long,    // 로그인 구현 전까지 사용할 임시 필드
    val bingsooId: Long,
    val topping: ToppingDto,
    val toppingTypeId: Long,
    val quiz: QuizDto?
)