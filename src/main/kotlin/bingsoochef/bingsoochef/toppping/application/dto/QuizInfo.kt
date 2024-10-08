package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Question
import bingsoochef.bingsoochef.toppping.domain.Quiz
import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizInfo(
    val quizId: Long,
    val quizType: QuizType,
    val title: String,
    val wrongCount: Short,
    val questions: List<QuestionInfo>
) {

    class QuestionInfo (
        val questionid: Long,
        val content: String
    ) {
        companion object {
            fun from(question: Question): QuestionInfo {
                return QuestionInfo(question.id!!, question.content)
            }
        }
    }

    companion object {
        fun of(quiz: Quiz, questions: List<Question>): QuizInfo {
            return QuizInfo(
                quiz.id!!,
                quiz.quizType,
                quiz.title,
                quiz.wrongCount,
                questions.map { QuestionInfo.from(it) }
            )
        }
    }
}