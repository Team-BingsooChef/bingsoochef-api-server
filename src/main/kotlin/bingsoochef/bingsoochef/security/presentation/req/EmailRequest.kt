package bingsoochef.bingsoochef.security.presentation.req

import jakarta.validation.constraints.Email

class EmailRequest(
    @Email
    val email: String
) {
}