package bingsoochef.bingsoochef.toppping.presentation;

import bingsoochef.bingsoochef.toppping.presentation.res.ToppingTypeResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/topping_types")
class ToppingTypeController {

    @Operation(
        summary = "토핑 종류 조회 API",
        description = "토핑의 종류 목록을 반환합니다."
    )
    @ApiResponse(responseCode = "200", description = "토핑의 종류 목록 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ToppingTypeResponse::class))))])
    @GetMapping()
    fun getToppingTypes(): ResponseEntity<ToppingTypeResponse> {
        TODO()
    }
}
