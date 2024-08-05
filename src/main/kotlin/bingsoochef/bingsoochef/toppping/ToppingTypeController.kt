package bingsoochef.bingsoochef.toppping;

import bingsoochef.bingsoochef.toppping.res.ToppingTypeResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/topping_types")
class ToppingTypeController {

    @GetMapping()
    fun getToppingTypes(): ResponseEntity<ToppingTypeResponse> {
        TODO()
    }
}
