package bingsoochef.bingsoochef.toppping

import bingsoochef.bingsoochef.toppping.req.CreateToppingRequest
import bingsoochef.bingsoochef.toppping.res.ToppingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/users/bingsoos/toppings")
class ToppingController {

    @PostMapping
    fun createTopping(@RequestBody request: CreateToppingRequest): ResponseEntity<ToppingResponse> {
        TODO()
    }
}