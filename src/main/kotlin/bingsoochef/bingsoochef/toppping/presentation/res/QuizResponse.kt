package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizResponse(
    val quiz: QuizDto,
    val questions: List<QuestionDto>
) {
    class QuizDto(
        val quizId: Long,
        val quizTitle: String,
        val quizType: QuizType,
        val wrongCount: Short
    )

    class QuestionDto(
        val questionId: Long,
        val questionContent: String
    )
}
