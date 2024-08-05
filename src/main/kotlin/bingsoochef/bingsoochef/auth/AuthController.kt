package bingsoochef.bingsoochef.auth

import bingsoochef.bingsoochef.presentation.req.LoginRequest
import bingsoochef.bingsoochef.presentation.res.LoginResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class AuthController : AuthControllerInterface {

    @PostMapping("/basic")
    override fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        val response = LoginResponse("accessToken", 3600, "refreshToken", 3600)

        return ResponseEntity.ok()
            .body(response)
    }

    @GetMapping("/oauth/kakao")
    override fun kakaoOauth(): ResponseEntity<Void> {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .header("Location", "")
            .build()
    }

    @GetMapping("/oauth/google")
    override fun googleOauth(): ResponseEntity<Void> {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .header("Location", "")
            .build()
    }

    @GetMapping("/oauth/naver")
    override fun naverOauth(): ResponseEntity<Void> {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .header("Location", "")
            .build()
    }
}