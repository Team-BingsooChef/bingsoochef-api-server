package bingsoochef.bingsoochef.toppping.application

import bingsoochef.bingsoochef.bingsoo.persistence.BingsooRepository
import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.BingsooError
import bingsoochef.bingsoochef.common.exception.code.ToppingError
import bingsoochef.bingsoochef.common.exception.code.UserError
import bingsoochef.bingsoochef.toppping.application.command.CreateToppingCommand
import bingsoochef.bingsoochef.toppping.application.command.GetToppingCommand
import bingsoochef.bingsoochef.toppping.application.command.GetToppingPageCommand
import bingsoochef.bingsoochef.toppping.application.command.RegisterCommentCommand
import bingsoochef.bingsoochef.toppping.application.dto.*
import bingsoochef.bingsoochef.toppping.domain.*
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

        return Pair(ToppingInfo.from(topping), CommentInfo.from(topping.comment!!))
    }

    @Transactional(readOnly = true)
    fun getQuiz(userId: Long, toppingId: Long): Pair<QuizInfo, List<QuestionInfo>> {

        val user = userRepository.findById(userId)
            .orElseThrow{ BingsooException(UserError.USER_NOT_FOUND) }

        val quiz = quizRepository.findByToppingId(toppingId)
            .orElseThrow{ BingsooException(ToppingError.QUIZ_NOT_FOUND) }

        quiz.isReadableBy(user)

        val questions = questionRepository.findAllByQuiz(quiz)

        return Pair(QuizInfo.from(quiz), questions.map { QuestionInfo.from(it) })
    }

    fun tryQuiz(userId: Long, quizId: Long, questionId: Long): TryResultInfo {

        val user = userRepository.findById(userId)
            .orElseThrow{ BingsooException(UserError.USER_NOT_FOUND) }
        val quiz = quizRepository.findById(quizId)
            .orElseThrow{ BingsooException(ToppingError.QUIZ_NOT_FOUND) }

        quiz.isTryableBy(user)

        val question = questionRepository.findById(questionId)
            .orElseThrow{ BingsooException(ToppingError.QUESTION_NOT_FOUND) }

        val result: Boolean = question.isAnswer
        when (result) {
            true -> quiz.topping.defrost()
            false -> quiz.getQuizWrong()
        }

        return TryResultInfo.of(result, quiz)
    }

    fun registerComment(command: RegisterCommentCommand): Pair<ToppingInfo, CommentInfo> {

        val user = userRepository.findById(command.userId)
            .orElseThrow{ BingsooException(UserError.USER_NOT_FOUND) }
        val topping = toppingRepository.findById(command.toppingId)
            .orElseThrow{ BingsooException(ToppingError.TOPPING_NOT_FOUND) }

        topping.isReadableBy(user)
        if (topping.comment != null)
            throw BingsooException(ToppingError.COMMENT_DUPLICATE)

        val comment = Comment(
            content = command.commentContent,
            createdTime = LocalDateTime.now()
        )
        topping.comment = comment

        commentRepository.save(comment)

        return Pair(ToppingInfo.from(topping), CommentInfo.from(comment))
    }
}