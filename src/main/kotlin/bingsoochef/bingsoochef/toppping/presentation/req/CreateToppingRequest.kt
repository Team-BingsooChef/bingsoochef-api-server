package bingsoochef.bingsoochef.toppping.presentation.req

data class CreateToppingRequest (
    val bingsooId: Long,
    val toping: ToppingDto,
    val quiz: QuizDto
)