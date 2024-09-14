package bingsoochef.bingsoochef.security.presentation

import bingsoochef.bingsoochef.security.presentation.req.EmailRequest
import bingsoochef.bingsoochef.security.presentation.req.SignupRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity

@Tag(name = "인증 담당 API", description = "인증에 관련된 API를 담당합니다.")
interface AuthenticationControllerInterface {

    @Operation(summary = "이메일 인증", description = "해당하는 이메일에 인증 코드를 전송합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "이메일 인증 코드 전송 성공"),
        ]
    )
    fun authenticateEmail(
        @RequestBody(required = true, description = "이메일 정보입니다.") @Valid request: EmailRequest
    ): ResponseEntity<Void>

    @Operation(summary = "이메일 인증 코드 확인", description = "해당하는 이메일의 인증 코드를 확인합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "이메일 인증 성공")
        ]
    )
    fun verifyEmailCode(
        @RequestBody(required = true, description = "이메일 정보입니다.") @Valid request: EmailRequest,
        @Parameter(name = "code", description = "이메일 인증 코드입니다.") code: String
    ): ResponseEntity<Void>

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "회원가입 성공, 성공 시 세부 정보 설정 페이지로 Redirect")
        ]
    )
    fun signup(
        @RequestBody(required = true, description = "회원가입 기본 정보.") @Valid request: SignupRequest
    ): ResponseEntity<Void>
}