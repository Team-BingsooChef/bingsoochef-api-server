package bingsoochef.bingsoochef.toppping.res

import bingsoochef.bingsoochef.toppping.req.QuizDto

data class QuizResponse(
    val quiz: QuizDto,
    val options: List<OptionDto>
)
