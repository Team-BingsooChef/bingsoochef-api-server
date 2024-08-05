package bingsoochef.bingsoochef.toppping.req

data class CreateToppingRequest (
    val bingsooId: Long,
    val toping: ToppingDto,
    val quiz: QuizDto
)