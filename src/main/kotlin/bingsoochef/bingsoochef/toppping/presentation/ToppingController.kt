package bingsoochef.bingsoochef.toppping.presentation

import bingsoochef.bingsoochef.toppping.application.CreateToppingCommand
import bingsoochef.bingsoochef.toppping.application.ToppingService
import bingsoochef.bingsoochef.toppping.domain.Comment
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
        val command = CreateToppingCommand.of(request.userId, request)

        val topping : Topping = toppingService.createTopping(command)

        val toppingResponse = convertToToppingResponse(topping = topping, comment = null)

        return ResponseEntity.status(HttpStatus.CREATED).body(toppingResponse)
    }

    @GetMapping("")
    override fun getToppingPage(
        @RequestParam(value = "b") bingsooId: Long,
        @PageableDefault(page = 0, size = 8) pageable: Pageable ): ResponseEntity<ToppingPageResponse> {
        TODO()
    }

    @GetMapping("/{topping-id}")
    override fun getTopping(@PathVariable(value = "topping-id") toppingId: Long,
                            @RequestParam(value = "user-id") userId: Long): ResponseEntity<ToppingResponse> {
        TODO()
    }

    @GetMapping("/{topping-id}/quiz")
    override fun getQuiz(@PathVariable(value = "topping-id") toppingId: Long,
                         @RequestParam(value = "user-id") userId: Long): ResponseEntity<QuizResponse> {
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

    private fun convertToToppingResponse(topping: Topping, comment: Comment?): ToppingResponse {
        val toppingDto = ToppingDto.from(topping)

        return ToppingResponse(topping = toppingDto, comment = null)
    }
}