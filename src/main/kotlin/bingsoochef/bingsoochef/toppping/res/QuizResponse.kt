package bingsoochef.bingsoochef.toppping.res

data class QuizResponse(
    val quiz: QuizDto,
    val options: List<OptionDto>
)
