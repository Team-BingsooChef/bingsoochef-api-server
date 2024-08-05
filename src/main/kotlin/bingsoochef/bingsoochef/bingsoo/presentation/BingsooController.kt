package bingsoochef.bingsoochef.bingsoo.presentation

import bingsoochef.bingsoochef.bingsoo.presentation.req.CreateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.req.UpdateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.res.BingsooDetailResponse
import bingsoochef.bingsoochef.bingsoo.presentation.res.BingsooResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/users/bingsoo")
class BingsooController {

    @PostMapping
    fun createBingsoo(@RequestBody request: CreateBingsooRequest): ResponseEntity<BingsooResponse> {
        TODO()
    }

    @GetMapping("/{bingsoo_id}")
    fun getBingsoo(@PathVariable(value = "bingsoo_id") bingsooId: Long): ResponseEntity<BingsooDetailResponse> {
        TODO()
    }

    @PatchMapping()
    fun updateBingsoo(@RequestBody request: UpdateBingsooRequest): ResponseEntity<BingsooResponse> {
        TODO()
    }
}