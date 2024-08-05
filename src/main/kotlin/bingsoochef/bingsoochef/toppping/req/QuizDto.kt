package bingsoochef.bingsoochef.toppping.req

import bingsoochef.bingsoochef.toppping.domain.QuizType

data class QuizDto(
    val quizTitle: String,
    val quizType: QuizType,
    val options: List<Pair<String, Boolean>>
)