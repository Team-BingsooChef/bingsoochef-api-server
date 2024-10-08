package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.application.dto.QuestionInfo
import bingsoochef.bingsoochef.toppping.application.dto.QuizInfo
import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizResponse(
    val quiz: QuizDto,
    val questions: List<QuestionDto>
) {
    class QuizDto(
        val quizId: Long,
        val quizType: QuizType,
        val quizTitle: String,
        val wrongCount: Short
    )

    class QuestionDto(
        val questionId: Long,
        val questionContent: String
    )

    companion object {
        fun of(quizInfo: QuizInfo, questionsInfo: List<QuestionInfo>): QuizResponse {
            return QuizResponse(
                quiz = QuizDto(
                    quizId = quizInfo.quizId,
                    quizType = quizInfo.quizType,
                    quizTitle = quizInfo.title,
                    wrongCount = quizInfo.wrongCount
                ),
                questions = questionsInfo.map { q -> QuestionDto(q.id, q.content) }
            )
        }
    }
}
