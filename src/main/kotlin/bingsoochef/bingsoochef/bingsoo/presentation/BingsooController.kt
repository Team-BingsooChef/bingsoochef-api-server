package bingsoochef.bingsoochef.bingsoo.presentation

import bingsoochef.bingsoochef.bingsoo.application.BingsooService
import bingsoochef.bingsoochef.bingsoo.application.command.CreateBingsooCommand
import bingsoochef.bingsoochef.bingsoo.application.command.UpdateBingsooCommand
import bingsoochef.bingsoochef.bingsoo.presentation.req.CreateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.req.UpdateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.res.BingsooResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController()
@RequestMapping("/users/bingsoo")
class BingsooController(
    private val bingsooService : BingsooService
): BingsooControllerInterface {

    @PostMapping
    override fun createBingsoo(@RequestBody request: CreateBingsooRequest) : ResponseEntity<BingsooResponse> {
        // TODO("user id를 access token에서 가져오도록 수정")
        val userId = request.userId

        val createCommand = CreateBingsooCommand(userId, request.taste)

        val info = bingsooService.createBingsoo(createCommand)
        val bingsooResponse = BingsooResponse.from(info)

        return ResponseEntity.status(HttpStatus.CREATED).body(bingsooResponse)
    }

    @GetMapping("/{bingsoo-id}")
    override fun getBingsoo(@PathVariable(value = "bingsoo-id") bingsooId: Long): ResponseEntity<BingsooResponse> {
        val info = bingsooService.getBingsoo(bingsooId)
        val bingsooResponse = BingsooResponse.from(info)

        return ResponseEntity.status(HttpStatus.OK).body(bingsooResponse)
    }

    @PatchMapping()
    override fun updateBingsoo(@RequestBody request: UpdateBingsooRequest): ResponseEntity<BingsooResponse> {
        // TODO("user id를 access token에서 가져오도록 수정")
        val userId = request.userId

        val updateCommand = UpdateBingsooCommand(userId, request.taste)

        val info = bingsooService.updateBingsoo(updateCommand)
        val bingsooResponse = BingsooResponse.from(info)

        return ResponseEntity.status(HttpStatus.OK).body(bingsooResponse)
    }
}