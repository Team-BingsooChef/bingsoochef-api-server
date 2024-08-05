package bingsoochef.bingsoochef.auth

import bingsoochef.bingsoochef.presentation.req.LoginRequest
import bingsoochef.bingsoochef.presentation.res.LoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class AuthController {

    @Operation(summary = "Login")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Login Success")
    )
    @PostMapping("/basic")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        val response = LoginResponse("accessToken", 3600, "refreshToken", 3600)

        return ResponseEntity.ok()
            .body(response)
    }

    @Operation(summary = "Kakao OAuth")
    @ApiResponses(
        ApiResponse(
            responseCode = "SEE OTHER",
            headers = [Header(name = "Location", description = "Redirect URL")],
            description = "Kakao OAuth로 Redirect"
        )
    )
    @GetMapping("/oauth/kakao")
    fun kakaoOauth(): ResponseEntity<Void> {
        return ResponseEntity.status(303)
            .header("Location", "")
            .build()
    }
}