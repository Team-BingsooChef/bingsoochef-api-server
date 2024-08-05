package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizDto(
    val quizId: Long,
    val quizTitle: String,
    val quizType: QuizType,
    val wrongCount: Short
)
