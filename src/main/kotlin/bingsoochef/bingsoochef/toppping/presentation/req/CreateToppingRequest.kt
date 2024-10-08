package bingsoochef.bingsoochef.toppping.presentation.req

import bingsoochef.bingsoochef.toppping.application.command.CreateToppingCommand
import bingsoochef.bingsoochef.toppping.domain.QuizType

data class CreateToppingRequest (
    val userId: Long,    // 로그인 구현 전까지 사용할 임시 필드
    val bingsooId: Long,
    val topping: ToppingDto,
    val toppingTypeId: Long,
    val quiz: QuizDto?
) {
    class ToppingDto(
        val chefName: String,
        val toppingTitle: String,
        val toppingContent: String
    )

    class QuizDto(
        val quizTitle: String,
        val quizType: QuizType,
        val questions: List<Pair<String, Boolean>>
    )

    fun toCommand(userId: Long): CreateToppingCommand {
        return CreateToppingCommand(
            bingsooId = bingsooId,
            chefId = userId,
            chefName = topping.chefName,
            toppingTitle = topping.toppingTitle,
            toppingContent = topping.toppingContent,
            toppingTypeId = toppingTypeId,
            quizType = quiz?.quizType,
            quizTitle = quiz?.quizTitle,
            questions = quiz?.questions
        )
    }
}