package bingsoochef.bingsoochef.toppping.presentation.req

import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizDto(
    val quizTitle: String,
    val quizType: QuizType,
    val questions: List<Pair<String, Boolean>>
)