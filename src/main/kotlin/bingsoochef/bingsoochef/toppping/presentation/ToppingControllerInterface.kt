package bingsoochef.bingsoochef.toppping.presentation

import bingsoochef.bingsoochef.toppping.presentation.req.CreateToppingRequest
import bingsoochef.bingsoochef.toppping.presentation.req.RegisterCommentRequest
import bingsoochef.bingsoochef.toppping.presentation.req.TryQuizRequest
import bingsoochef.bingsoochef.toppping.presentation.res.QuizResponse
import bingsoochef.bingsoochef.toppping.presentation.res.ToppingResponse
import bingsoochef.bingsoochef.toppping.presentation.res.ToppingPageResponse
import bingsoochef.bingsoochef.toppping.presentation.res.TryResultResponse
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
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Topping", description = "토핑 API")
interface ToppingControllerInterface {

    @Operation(
        summary = "토핑 생성 API",
        description = "Request body 속 bingsoo id를 가진 빙수에 토핑을 생성합니다.<br>" +
                "이때 주어진 access token 속 user id를 chef로 합니다."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "생성된 토핑 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ToppingResponse::class))))]),
        ApiResponse(responseCode = "400", description = "잘못된 QuizType을 요청함 / QuizType과 Option이 맞지 않음", content = [Content()]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 빙수임", content = [Content()])
    ])
    fun createTopping(@RequestBody request: CreateToppingRequest): ResponseEntity<ToppingResponse>

    @Operation(
        summary = "토핑 목록 조회 API",
        description = "bingsoo id를 가진 빙수의 토핑들을 조회합니다.<br>" +
                "토핑은 8의 크기로 페이지네이션을 적용해 반환되며, 전체 페이지 범위는 0 ~ total page number(totalPage)입니다.<br>" +
                "현재 페이지 번호가 전체 페이지 범위를 벗어날 경우 성공 응답에도 toppings의 정보가 반환되지 않습니다."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "조회한 토핑 목록 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ToppingPageResponse::class))))]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 빙수임", content = [Content()])
    ])
    fun getToppingPage(@RequestParam(value = "b") bingsooId: Long, @RequestParam(value = "p") page: Int): ResponseEntity<ToppingPageResponse>

    @Operation(
        summary = "토핑 조회 API",
        description = "topping id를 가진 토핑을 조회합니다.<br>" +
                "access token의 user id가 chef id나 topping이 속한 bingsoo의 customer id와 동일한 경우에만 토핑에 접근할 수 있습니다.<br>" +
                "access token의 user id가 customer id와 동일한 경우, topping의 is hiden이 false일 때만 토핑을 조회할 수 있으며 " +
                "is hidden이 true일 땐 성공 응답에도 topping의 정보가 반환되지 않습니다.<br>"
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "조회한 토핑 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ToppingResponse::class))))]),
        ApiResponse(responseCode = "401", description = "토핑에 접근할 수 없음 (셰프도 손님도 아님)", content = [Content()]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 토핑임", content = [Content()])
    ])
    fun getTopping(@PathVariable(value = "topping_id") toppingId: Long): ResponseEntity<ToppingResponse>


    @Operation(
        summary = "퀴즈 조회 API",
        description = "topping id를 가진 토핑의 퀴즈를 조회합니다.<br>" +
                "access token의 user id가 chef id나 topping이 속한 bingsoo의 customer id와 동일한 경우에만 퀴즈에 접근할 수 있습니다.<br>" +
                "토핑에 퀴즈가 없거나, 이미 해결한 퀴즈인 경우 실패로 응답합니다."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "조회한 퀴즈 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = QuizResponse::class))))]),
        ApiResponse(responseCode = "401", description = "토핑에 접근할 수 없음 (셰프도 손님도 아님)", content = [Content()]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 토핑/퀴즈이거나 이미 해결한 퀴즈", content = [Content()])
    ])
    fun getQuiz(@PathVariable(value = "topping_id") toppingId: Long): ResponseEntity<QuizResponse>


    @Operation(
        summary = "퀴즈 정답 제출 API",
        description = "quiz id를 가진 퀴즈에 정답을 제출합니다.<br>" +
                "access token의 user id가 toppping에 대한 chef id나 customer id와 동일한 경우에만 제출을 시도할 수 있습니다.<br>" +
                "토핑에 퀴즈가 없거나, 이미 해결한 퀴즈인 경우 실패로 응답합니다."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "정답 제출 결과 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = TryResultResponse::class))))]),
        ApiResponse(responseCode = "401", description = "퀴즈에 접근할 수 없음 (셰프도 손님도 아님)", content = [Content()]),
        ApiResponse(responseCode = "404", description = "존재하지 않거나 이미 해결한 퀴즈 / 존재하지 않는 옵션", content = [Content()])
    ])
    fun tryQuiz(@RequestBody request: TryQuizRequest): ResponseEntity<TryResultResponse>


    @Operation(
        summary = "코멘트 등록 API",
        description = "topping id를 가진 토핑에 comment를 등록합니다.<br>" +
                "access token의 user id가 toppping에 대한 chef id나 customer id와 동일한 경우에만 comment를 등록할 수 있습니다.<br>" +
                "access token의 user id가 customer id와 동일한 경우, topping의 is hiden이 false일 때만 comment를 등록할 수 있으며 " +
                "is hidden이 true일 땐 성공 응답에도 comment가 등록되지 않습니다."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "생성된 코멘트를 포함한 토핑 반환",
            content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ToppingResponse::class))))]),
        ApiResponse(responseCode = "400", description = "이미 코멘트가 등록된 토핑", content = [Content()]),
        ApiResponse(responseCode = "401", description = "토핑에 접근할 수 없음 (셰프도 손님도 아님)", content = [Content()]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 토핑", content = [Content()])
    ])
    fun registerComment(@RequestBody request: RegisterCommentRequest): ResponseEntity<ToppingResponse>
}