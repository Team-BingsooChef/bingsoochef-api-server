package bingsoochef.bingsoochef.toppping

import bingsoochef.bingsoochef.toppping.req.CreateToppingRequest
import bingsoochef.bingsoochef.toppping.req.RegisterCommentRequest
import bingsoochef.bingsoochef.toppping.req.TryQuizRequest
import bingsoochef.bingsoochef.toppping.res.QuizResponse
import bingsoochef.bingsoochef.toppping.res.ToppingResponse
import bingsoochef.bingsoochef.toppping.res.TryResultResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/users/bingsoos/toppings")
class ToppingController {

    @PostMapping
    fun createTopping(@RequestBody request: CreateToppingRequest): ResponseEntity<ToppingResponse> {
        TODO()
    }

    @GetMapping("/{topping_id}")
    fun getTopping(@PathVariable(value = "topping_id") toppingId: Long): ResponseEntity<ToppingResponse> {
        TODO()
    }

    @GetMapping("/{topping_id}/quiz")
    fun getQuiz(@PathVariable(value = "topping_id") toppingId: Long): ResponseEntity<QuizResponse> {
        TODO()
    }

    @PostMapping("/quiz")
    fun tryQuiz(@RequestBody request: TryQuizRequest): ResponseEntity<TryResultResponse> {
        TODO()
    }

    @PostMapping("/comments")
    fun registerComment(@RequestBody request: RegisterCommentRequest): ResponseEntity<ToppingResponse> {
        TODO()
    }
}