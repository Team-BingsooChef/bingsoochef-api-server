package bingsoochef.bingsoochef.security.application


import java.security.SecureRandom

object AuthenticationUtil {
    private val random: SecureRandom = SecureRandom()

    fun generateCertificationNumber(): String {
        val certificationNumber = StringBuilder()
        for (i in 0 until 6) {
            certificationNumber.append(random.nextInt(10))
        }
        return certificationNumber.toString()
    }
}