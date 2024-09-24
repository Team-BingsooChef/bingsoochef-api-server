package bingsoochef.bingsoochef.toppping.application

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.persistence.BingsooRepository
import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.BingsooError
import bingsoochef.bingsoochef.common.exception.code.ToppingError
import bingsoochef.bingsoochef.common.exception.code.UserError
import bingsoochef.bingsoochef.global.error.NotFoundException
import bingsoochef.bingsoochef.toppping.application.dto.CommentInfo
import bingsoochef.bingsoochef.toppping.application.dto.ToppingInfo
import bingsoochef.bingsoochef.toppping.application.dto.ToppingPageInfo
import bingsoochef.bingsoochef.toppping.domain.Question
import bingsoochef.bingsoochef.toppping.domain.Quiz
import bingsoochef.bingsoochef.toppping.domain.QuizType
import bingsoochef.bingsoochef.toppping.domain.Topping
import bingsoochef.bingsoochef.toppping.persistence.*
import bingsoochef.bingsoochef.user.persistence.UserRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@Transactional
@Service
class ToppingService(
    private val userRepository: UserRepository,
    private val toppingRepository: ToppingRepository,
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository,
    private val toppingTypeRepository: ToppingTypeRepository,
    private val bingsooRepository: BingsooRepository,
    private val commentRepository: CommentRepository
) {

    fun createTopping(command: CreateToppingCommand): ToppingInfo {

        val bingsoo = bingsooRepository.findById(command.bingsooId)
            .orElseThrow { BingsooException(BingsooError.NOT_FOUND) }
        val toppingType = toppingTypeRepository.findById(command.toppingTypeId)
            .orElseThrow { BingsooException(ToppingError.TOPPING_TYPE_NOT_FOUND) }
        val chef = userRepository.findById(command.chefId)
            .orElseThrow { BingsooException(UserError.USER_NOT_FOUND) }

        if (toppingRepository.existsByBingsooAndChef(bingsoo, chef))
            throw BingsooException(ToppingError.TOPPING_DUPLICATE)

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

    @Transactional(readOnly = true)
    fun getToppingPage(command: GetToppingPageCommand): ToppingPageInfo {
        val bingsoo = bingsooRepository.findById(command.bingsooId)
            .orElseThrow{ BingsooException(BingsooError.NOT_FOUND) }

        val toppingPage : Page<Topping> = toppingRepository.findAllByBingsoo(bingsoo, command.pageable)

        return ToppingPageInfo.from(toppingPage)
    }

    @Transactional(readOnly = true)
    fun getTopping(command: GetToppingCommand): Pair<ToppingInfo, CommentInfo?> {
        val user = userRepository.findById(command.userId)
            .orElseThrow{ BingsooException(UserError.USER_NOT_FOUND) }

        val topping = toppingRepository.findById(command.toppingId)
            .orElseThrow{ BingsooException(ToppingError.TOPPING_NOT_FOUND) }

        topping.isReadableBy(user)

        if (topping.comment == null)
            return Pair(ToppingInfo.from(topping), null)

        val comment = commentRepository.findById(topping.comment!!.id!!)
            .orElseThrow{ BingsooException(ToppingError.COMMENT_NOT_FOUND) }

        return Pair(ToppingInfo.from(topping), CommentInfo.from(comment))
    }


}