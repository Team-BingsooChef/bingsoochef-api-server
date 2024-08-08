package bingsoochef.bingsoochef.toppping.presentation

import bingsoochef.bingsoochef.toppping.presentation.req.CreateToppingRequest
import bingsoochef.bingsoochef.toppping.presentation.req.RegisterCommentRequest
import bingsoochef.bingsoochef.toppping.presentation.req.TryQuizRequest
import bingsoochef.bingsoochef.toppping.presentation.res.QuizResponse
import bingsoochef.bingsoochef.toppping.presentation.res.ToppingResponse
import bingsoochef.bingsoochef.toppping.presentation.res.TryResultResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Topping", description = "토핑 API")
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