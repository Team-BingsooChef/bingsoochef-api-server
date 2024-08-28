package bingsoochef.bingsoochef.bingsoo.presentation

import bingsoochef.bingsoochef.bingsoo.presentation.req.CreateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.req.UpdateBingsooRequest
import bingsoochef.bingsoochef.bingsoo.presentation.res.BingsooResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Bingsoo", description = "빙수 API")
interface BingsooControllerInterface {

    @Operation(
        summary = "빙수 생성 API",
        description = "주어진 access token 속 user id를 가진 사용자에게 빙수를 생성합니다."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "생성된 빙수 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = BingsooResponse::class))))]),
        ApiResponse(responseCode = "400", description = "taste를 잘못 요청함", content = [Content()])
    ])
    fun createBingsoo(request: CreateBingsooRequest): ResponseEntity<BingsooResponse>


    @Operation(
        summary = "빙수 조회 API",
        description = "bingsoo id를 통해 자신 혹은 다른 사용자의 빙수를 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "조회한 빙수 반환",
        content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = BingsooResponse::class))))]
    )
    fun getBingsoo(@PathVariable(value = "bingsoo_id") bingsooId: Long): ResponseEntity<BingsooResponse>


    @Operation(
        summary = "빙수 수정 API",
        description = "주어진 access token 속 bingsoo id를 가진 빙수의 맛을 요청한 값으로 수정합니다."
    )
    @ApiResponse(responseCode = "200", description = "수정된 빙수 반환",
        content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = BingsooResponse::class))))]
    )
    fun updateBingsoo(@RequestBody request: UpdateBingsooRequest): ResponseEntity<BingsooResponse>
}