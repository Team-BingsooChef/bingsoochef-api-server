package bingsoochef.bingsoochef.toppping.presentation.req

data class CreateToppingRequest (
    val bingsooId: Long,
    val topping: ToppingDto,
    val quiz: QuizDto
)