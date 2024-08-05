package bingsoochef.bingsoochef.presentation.res

data class LoginResponse(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int
)
