package bingsoochef.bingsoochef.toppping.presentation

import bingsoochef.bingsoochef.toppping.application.CreateToppingCommand
import bingsoochef.bingsoochef.toppping.application.ToppingService
import bingsoochef.bingsoochef.toppping.domain.Topping
import bingsoochef.bingsoochef.toppping.presentation.req.CreateToppingRequest
import bingsoochef.bingsoochef.toppping.presentation.req.RegisterCommentRequest
import bingsoochef.bingsoochef.toppping.presentation.req.TryQuizRequest
import bingsoochef.bingsoochef.toppping.presentation.res.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/users/bingsoos/toppings")
class ToppingController(
    private val toppingService: ToppingService
) : ToppingControllerInterface {

    @PostMapping
    override fun createTopping(@RequestBody request: CreateToppingRequest): ResponseEntity<ToppingResponse> {
        val command =
            CreateToppingCommand(
                bingsooId = request.bingsooId,
                chefId = request.userId,
                chefName = request.topping.chefName,
                toppingTitle = request.topping.toppingTitle,
                toppingContent = request.topping.toppingContent,
                toppingTypeId = request.toppingTypeId,
                quizType = request.quiz?.quizType,
                quizTitle = request.quiz?.quizTitle,
                questions = request.quiz?.questions
            )

        val topping : Topping = toppingService.createTopping(command)

        val toppingDto = ToppingDto.from(topping)
        val toppingResponse = ToppingResponse(topping = toppingDto, comment = null)

        return ResponseEntity.status(HttpStatus.CREATED).body(toppingResponse)
    }

    @GetMapping("")
    override fun getToppingPage(
        @RequestParam(value = "b") bingsooId: Long,
        @PageableDefault(page = 0, size = 8) pageable: Pageable ): ResponseEntity<ToppingPageResponse> {
        TODO()
    }

    @GetMapping("/{topping-id}")
    override fun getTopping(@PathVariable(value = "topping-id") toppingId: Long): ResponseEntity<ToppingResponse> {
        TODO()
    }

    @GetMapping("/{topping-id}/quiz")
    override fun getQuiz(@PathVariable(value = "topping-id") toppingId: Long): ResponseEntity<QuizResponse> {
        TODO()
    }

    @PostMapping("/quiz")
    override fun tryQuiz(@RequestBody request: TryQuizRequest): ResponseEntity<TryResultResponse> {
        TODO()
    }

    @PostMapping("/comments")
    override fun registerComment(@RequestBody request: RegisterCommentRequest): ResponseEntity<ToppingResponse> {
        TODO()
    }
}