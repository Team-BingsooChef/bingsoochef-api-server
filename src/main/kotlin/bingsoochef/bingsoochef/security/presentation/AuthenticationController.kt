package bingsoochef.bingsoochef.security.presentation

import bingsoochef.bingsoochef.security.application.AuthenticationService
import bingsoochef.bingsoochef.security.presentation.req.EmailRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) : AuthenticationControllerInterface {

    @PostMapping("/email")
    override fun authenticateEmail(
        @RequestBody request: EmailRequest
    ): ResponseEntity<Void> {
        authenticationService.sendMail(request.email)

        return ResponseEntity.ok()
            .build()
    }

    @PostMapping("/email/verify")
    override fun verifyEmailCode(
        @RequestBody request: EmailRequest,
        @RequestParam code: String
    ): ResponseEntity<Void> {

        return ResponseEntity.ok()
            .build()
    }
}