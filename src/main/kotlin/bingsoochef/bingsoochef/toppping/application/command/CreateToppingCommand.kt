package bingsoochef.bingsoochef.toppping.application.command

import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.ToppingError
import bingsoochef.bingsoochef.toppping.domain.QuizType

const val chefNameLength = 8
const val toppingContentLength = 300
const val quizTitleLength = 15
const val questionLength = 30

data class CreateToppingCommand(
    val bingsooId: Long,
    val chefId: Long,

    val chefName: String,
    val toppingTitle: String,
    val toppingContent: String,

    val toppingTypeId: Long,

    val quizType : QuizType?,
    val quizTitle : String?,
    val questions : List<Pair<String, Boolean>>?,

    val isQuiz : Boolean =
        if (quizType != null && quizTitle != null) {
            if (quizType == QuizType.OX)
                validateOX(questions)
            if (quizType == QuizType.MULTIPLE_CHOICE)
                validateMultipleChoice(questions)
            true
        }
        else if (quizType == null && quizTitle == null)
            false
        else
            throw BingsooException(ToppingError.QUIZ_ILLEGAL_REQUEST, "퀴즈 타입과 제목은 모두 있거나 모두 없어야 합니다.")
) {
    init {
        if (chefName.length > chefNameLength)
            throw BingsooException(ToppingError.TOPPING_ILLEGAL_REQUEST, "셰프 이름은 ${chefNameLength}자 이하여야 합니다.")
        if (toppingContent.length > toppingContentLength)
            throw BingsooException(ToppingError.TOPPING_ILLEGAL_REQUEST, "토핑 내용은 ${toppingContentLength}자 이하여야 합니다.")

        if (isQuiz) {
            if (quizTitle!!.length > quizTitleLength)
                throw BingsooException(ToppingError.QUIZ_ILLEGAL_REQUEST, "퀴즈의 제목은 ${quizTitleLength}자 이하여야 합니다.")

            for (q in questions!!) {
                if (q.first.length > questionLength)
                    throw BingsooException(ToppingError.QUIZ_ILLEGAL_REQUEST, "퀴즈의 선지는 ${questionLength}자 이하여야 합니다.")
            }
        }
    }
}

private fun validateOX(questions : List<Pair<String, Boolean>>?)  {
    if (questions.isNullOrEmpty() || questions.size != 1 || (questions[0].first != "O" && questions[0].first != "X"))
        throw BingsooException(ToppingError.QUIZ_ILLEGAL_REQUEST, "OX 퀴즈는 O와 X 중 정답인 선지 하나를 가져야 합니다.")
}

private fun validateMultipleChoice(questions: List<Pair<String, Boolean>>?)  {
    if (questions.isNullOrEmpty() )
        throw BingsooException(ToppingError.QUIZ_ILLEGAL_REQUEST, "객관식 퀴즈는 선지가 1개 이상이어야 합니다.")
    if ( questions.count { it.second } != 1)
        throw BingsooException(ToppingError.QUIZ_ILLEGAL_REQUEST, "객관식 퀴즈의 정답 선지는 1개입니다.")
}