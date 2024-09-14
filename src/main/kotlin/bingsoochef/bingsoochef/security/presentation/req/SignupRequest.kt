package bingsoochef.bingsoochef.security.presentation.req

import jakarta.validation.constraints.Email

class SignupRequest(
    @Email
    val username: String,
    val password: String
)