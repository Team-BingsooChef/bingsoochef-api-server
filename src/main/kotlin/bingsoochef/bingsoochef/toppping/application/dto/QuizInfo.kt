package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Question
import bingsoochef.bingsoochef.toppping.domain.Quiz
import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizInfo(
    val quizId: Long,
    val quizType: QuizType,
    val title: String,
    val wrongCount: Short
) {
    companion object {
        fun from(quiz: Quiz): QuizInfo {
            return QuizInfo(
                quiz.id!!,
                quiz.quizType,
                quiz.title,
                quiz.wrongCount
            )
        }
    }
}