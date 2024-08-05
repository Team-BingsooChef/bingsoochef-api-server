package bingsoochef.bingsoochef.auth

import bingsoochef.bingsoochef.presentation.req.LoginRequest
import bingsoochef.bingsoochef.presentation.res.LoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity

interface AuthControllerInterface {
    @Operation(summary = "Login")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Login Success")
    )
    fun login(loginRequest: LoginRequest): ResponseEntity<LoginResponse>

    @Operation(summary = "Kakao OAuth")
    @ApiResponses(
        ApiResponse(
            responseCode = "SEE OTHER",
            headers = [Header(name = "Location", description = "Redirect URL")],
            description = "Kakao OAuth로 Redirect"
        )
    )
    fun kakaoOauth(): ResponseEntity<Void>

    @Operation(summary = "Google OAuth")
    @ApiResponses(
        ApiResponse(
            responseCode = "SEE OTHER",
            headers = [Header(name = "Location", description = "Redirect URL")],
            description = "Google OAuth로 Redirect"
        )
    )
    fun googleOauth(): ResponseEntity<Void>

    @Operation(summary = "Naver OAuth")
    @ApiResponses(
        ApiResponse(
            responseCode = "SEE OTHER",
            headers = [Header(name = "Location", description = "Redirect URL")],
            description = "Naver OAuth로 Redirect"
        )
    )
    fun naverOauth(): ResponseEntity<Void>
}