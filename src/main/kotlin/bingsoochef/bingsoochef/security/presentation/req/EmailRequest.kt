package bingsoochef.bingsoochef.security.presentation.req

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

class EmailRequest(
    @Email
    @NotNull
    val email: String
) {
}