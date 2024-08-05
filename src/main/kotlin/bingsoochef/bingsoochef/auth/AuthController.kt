package bingsoochef.bingsoochef.auth

import bingsoochef.bingsoochef.presentation.req.LoginRequest
import bingsoochef.bingsoochef.presentation.res.LoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    
}