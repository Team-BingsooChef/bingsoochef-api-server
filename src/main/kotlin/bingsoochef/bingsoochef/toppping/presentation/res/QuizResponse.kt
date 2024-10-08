package bingsoochef.bingsoochef.toppping.presentation.res

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
    ) {
        companion object {
            fun from(info: QuizInfo.QuestionInfo): QuestionDto {
                return QuestionDto(
                    questionId = info.questionid,
                    questionContent = info.content
                )
            }
        }
    }

    companion object {
        fun from(info: QuizInfo): QuizResponse {
            return QuizResponse(
                quiz = QuizDto(
                    quizId = info.quizId,
                    quizType = info.quizType,
                    quizTitle = info.title,
                    wrongCount = info.wrongCount
                ),
                questions = info.questions.map { QuestionDto.from(it) }
            )
        }
    }
}
