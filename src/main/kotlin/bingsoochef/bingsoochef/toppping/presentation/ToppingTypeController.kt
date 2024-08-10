package bingsoochef.bingsoochef.toppping.presentation;

import bingsoochef.bingsoochef.toppping.presentation.res.ToppingTypeResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/topping-types")
class ToppingTypeController : ToppingTypeControllerInterface {

    @GetMapping()
    override fun getToppingTypes(): ResponseEntity<ToppingTypeResponse> {
        TODO()
    }
}
