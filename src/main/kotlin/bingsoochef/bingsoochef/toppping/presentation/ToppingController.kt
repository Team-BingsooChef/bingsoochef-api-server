package bingsoochef.bingsoochef.toppping.presentation

import bingsoochef.bingsoochef.toppping.presentation.req.CreateToppingRequest
import bingsoochef.bingsoochef.toppping.presentation.req.RegisterCommentRequest
import bingsoochef.bingsoochef.toppping.presentation.req.TryQuizRequest
import bingsoochef.bingsoochef.toppping.presentation.res.QuizResponse
import bingsoochef.bingsoochef.toppping.presentation.res.ToppingPageResponse
import bingsoochef.bingsoochef.toppping.presentation.res.ToppingResponse
import bingsoochef.bingsoochef.toppping.presentation.res.TryResultResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/users/bingsoos/toppings")
class ToppingController : ToppingControllerInterface {

    @PostMapping
    override fun createTopping(@RequestBody request: CreateToppingRequest): ResponseEntity<ToppingResponse> {
        TODO()
    }

    @GetMapping("")
    override fun getToppingPage(@RequestParam(value = "b") bingsooId: Long, @RequestParam(value = "p") page: Int): ResponseEntity<ToppingPageResponse> {
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