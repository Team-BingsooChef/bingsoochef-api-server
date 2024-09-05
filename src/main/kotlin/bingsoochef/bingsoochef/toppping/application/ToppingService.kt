package bingsoochef.bingsoochef.toppping.application

import bingsoochef.bingsoochef.bingsoo.persistence.BingsooRepository
import bingsoochef.bingsoochef.global.error.DuplicateException
import bingsoochef.bingsoochef.global.error.NotFoundException
import bingsoochef.bingsoochef.toppping.application.dto.ToppingInfo
import bingsoochef.bingsoochef.toppping.domain.Question
import bingsoochef.bingsoochef.toppping.domain.Quiz
import bingsoochef.bingsoochef.toppping.domain.QuizType
import bingsoochef.bingsoochef.toppping.domain.Topping
import bingsoochef.bingsoochef.toppping.persistence.QuestionRepository
import bingsoochef.bingsoochef.toppping.persistence.QuizRepository
import bingsoochef.bingsoochef.toppping.persistence.ToppingRepository
import bingsoochef.bingsoochef.toppping.persistence.ToppingTypeRepository
import bingsoochef.bingsoochef.user.persistence.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class ToppingService(
    private val userRepository: UserRepository,
    private val toppingRepository: ToppingRepository,
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository,
    private val toppingTypeRepository: ToppingTypeRepository,
    private val bingsooRepository: BingsooRepository
) {

    fun createTopping(command: CreateToppingCommand): ToppingInfo {

        val bingsoo = bingsooRepository.findById(command.bingsooId)
            .orElseThrow { NotFoundException("존재하지 않는 빙수입니다.") }
        val toppingType = toppingTypeRepository.findById(command.toppingTypeId)
            .orElseThrow { NotFoundException("존재하지 않는 토핑 유형입니다.") }
        val chef = userRepository.findById(command.chefId)
            .orElseThrow { NotFoundException("존재하지 않는 사용자입니다.") }

        if (toppingRepository.existsByBingsooAndChef(bingsoo, chef))
            throw DuplicateException("해당 빙수에 이미 토핑을 만들었습니다.")

        val topping = toppingRepository.save(
            Topping(
                id = null,
                chef = chef,
                bingsoo = bingsoo,
                comment = null,
                chefName = command.chefName,
                toppingType = toppingType,
                title = command.toppingTitle,
                content = command.toppingContent,
                position = toppingRepository.countByBingsooIs(bingsoo) + 1,
                createdTime = LocalDateTime.now(),
                isHidden = command.isQuiz
            )
        )

        if (command.isQuiz) {
            val quiz = quizRepository.save(
                Quiz(
                    id = null,
                    topping = topping,
                    title = command.quizTitle!!,
                    quizType = command.quizType!!
                )
            )
            questionRepository.saveAll(
                createQuestion(quiz, command.questions!!)
            )
        }

        return ToppingInfo.from(topping)
    }

    private fun createQuestion(quiz: Quiz, questions : List<Pair<String, Boolean>>): List<Question> {
        return when(quiz.quizType) {
            QuizType.OX -> {
                return listOf(
                    Question(
                        id = null,
                        quiz = quiz,
                        content = questions[0].first,
                        isAnswer = true
                    ),
                    Question(
                        id = null,
                        quiz = quiz,
                        content = if (questions[0].first == "O") "X" else "O",
                        isAnswer = false
                    ))
            }

            QuizType.MULTIPLE_CHOICE -> {
                questions.map { (c, a) ->
                    Question(
                        id = null,
                        quiz = quiz,
                        content = c,
                        isAnswer = a
                    )
                }
            }
        }
    }
}