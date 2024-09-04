package bingsoochef.bingsoochef.toppping.application

import bingsoochef.bingsoochef.toppping.domain.QuizType
import bingsoochef.bingsoochef.toppping.presentation.req.CreateToppingRequest

data class CreateToppingCommand(
    val bingsooId: Long,
    val chefId: Long,

    val chefName: String,
    val toppingTitle: String,
    val toppingContent: String,

    val toppingTypeId: Long,

    val quizType : QuizType?,
    val quizTitle : String?,
    val questions : List<Pair<String, Boolean>>?,

    val isQuiz : Boolean =
        if (quizType != null && quizTitle != null) {
            if (quizType == QuizType.OX && (questions.isNullOrEmpty() || questions.size != 1 || (questions[0].first != "O" && questions[0].first != "X")))
                throw IllegalArgumentException("OX 퀴즈는 O와 X 중 정답인 선지 하나를 가져야 합니다.")
            if (quizType == QuizType.MULTIPLE_CHOICE) {
                if (questions.isNullOrEmpty() )
                    throw IllegalArgumentException("객관식 퀴즈에는 선지가 1개 이상이어야 합니다.")
                if ( questions.count { it.second } != 1)
                    throw IllegalArgumentException("객관식 퀴즈에는 정답 선지는 1개입니다.")
            }
            true
        }
        else
            false
) {
    companion object {
        fun of(userId: Long, request: CreateToppingRequest): CreateToppingCommand {
            return CreateToppingCommand(
                bingsooId = request.bingsooId,
                chefId = userId,
                chefName = request.topping.chefName,
                toppingTitle = request.topping.toppingTitle,
                toppingContent = request.topping.toppingContent,
                toppingTypeId = request.toppingTypeId,
                quizType = request.quiz?.quizType,
                quizTitle = request.quiz?.quizTitle,
                questions = request.quiz?.questions
            )
        }
    }
}
