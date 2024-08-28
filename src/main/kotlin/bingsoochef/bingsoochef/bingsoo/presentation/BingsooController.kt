package bingsoochef.bingsoochef.bingsoo.presentation

import bingsoochef.bingsoochef.bingsoo.application.BingsooService
import bingsoochef.bingsoochef.bingsoo.application.CreateBingsooCommand
import bingsoochef.bingsoochef.bingsoo.application.UpdateBingsooCommand
import bingsoochef.bingsoochef.bingsoo.presentation.req.CreateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.req.UpdateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.res.BingsooDto
import bingsoochef.bingsoochef.bingsoo.presentation.res.BingsooResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/users/bingsoo")
class BingsooController(
    private val bingsooService : BingsooService
): BingsooControllerInterface {

    @PostMapping
    override fun createBingsoo(@RequestBody request: CreateBingsooRequest) : ResponseEntity<BingsooResponse> {
        // TODO("user id를 access token에서 가져오도록 수정")
        val userId = 1L

        val createCommand = CreateBingsooCommand(
            userId,
            request.taste
        )

        val bingsoo = bingsooService.createBingsoo(createCommand)
        val bingsooDto = BingsooDto.from(bingsoo)
        val bingsooResponse = BingsooResponse.of(bingsooDto)

        return ResponseEntity.status(HttpStatus.CREATED).body(bingsooResponse)
    }

    @GetMapping("/{bingsoo-id}")
    override fun getBingsoo(@PathVariable(value = "bingsoo-id") bingsooId: Long): ResponseEntity<BingsooResponse> {
        TODO()
    }

    @PatchMapping()
    override fun updateBingsoo(@RequestBody request: UpdateBingsooRequest): ResponseEntity<BingsooResponse> {
        // TODO("user id를 access token에서 가져오도록 수정")
        val userId = 1L

        val updateCommand = UpdateBingsooCommand(
            userId,
            request.taste
        )

        val bingsoo = bingsooService.updateBingsoo(updateCommand)
        val bingsooDto = BingsooDto.from(bingsoo)
        val bingsooResponse = BingsooResponse.of(bingsooDto)

        return ResponseEntity.status(HttpStatus.OK).body(bingsooResponse)
    }
}