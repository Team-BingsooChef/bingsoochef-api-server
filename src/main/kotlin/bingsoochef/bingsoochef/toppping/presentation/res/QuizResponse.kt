package bingsoochef.bingsoochef.toppping.presentation.res

data class QuizResponse(
    val quiz: QuizDto,
    val questions: List<QuestionDto>
)
