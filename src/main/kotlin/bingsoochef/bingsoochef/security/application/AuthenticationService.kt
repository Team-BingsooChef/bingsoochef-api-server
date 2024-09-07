package bingsoochef.bingsoochef.security.application

import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.AuthError
import bingsoochef.bingsoochef.infra.redis.RedisUtil
import bingsoochef.bingsoochef.user.domain.AccountType
import bingsoochef.bingsoochef.user.domain.User
import bingsoochef.bingsoochef.user.persistence.UserRepository
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class AuthenticationService(
    private val redisUtil: RedisUtil,
    private val mailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {
    companion object {
        private const val EMAIL_CERTIFICATION_EXPIRE_TIME = 5L
        private const val EMAIL_CERTIFICATION_SUCCESS_EXPIRE_MINUTE = 10L
    }

    @Transactional
    fun sendMail(email: String) {
        val certificationNumber = AuthenticationUtil.generateCertificationNumber()
        val htmlContent = createHtmlContent(certificationNumber)
        val message = mailSender.createMimeMessage()

        try {
            val helper = MimeMessageHelper(message, true, "UTF-8")
            helper.setTo(email)
            helper.setSubject("BingsooChef 이메일 인증")
            helper.setText(htmlContent, true)
            mailSender.send(message)
        } catch (e: MailException) {
            print(e.message)
            throw BingsooException(AuthError.EMAIL_SEND_FAILED)
        }
        saveSecretCode(email, certificationNumber)
    }

    fun verifyEmailCode(email: String, requestCode: String) {
        val code = getSecretCode(email)
        deleteSecretCode(email)
        if (isWrongCode(code, requestCode)) {
            throw BingsooException(AuthError.EMAIL_CERTIFICATION_FAILED)
        }
        saveSuccessCertification(email)
    }

    fun signup(username: String, password: String) {
        userRepository.findUserByUsername(username)?.let {
            throw BingsooException(AuthError.EMAIL_DUPLICATED)
        }
        println("username: $username")
        println("password: $password")
        if (isNotCertified(username)) {
            throw BingsooException(AuthError.EMAIL_CERTIFICATION_NOT_FOUND)
        }

        val encodedPassword = passwordEncoder.encode(password)
        val user = User(
            username = username,
            password = encodedPassword,
            userType = AccountType.LOCAL
        )
        userRepository.save(user)
    }

    private fun createHtmlContent(certificationNumber: String): String {
        val context = Context()
        context.setVariable("certificationNumber", certificationNumber)
        return templateEngine.process("email-certification-template", context)
    }

    private fun saveSecretCode(email: String, secretCode: String) {
        val key = redisUtil.getEmailCertificationKey(email)
        redisUtil.setData(key, secretCode, EMAIL_CERTIFICATION_EXPIRE_TIME, TimeUnit.MINUTES)
    }

    private fun saveSuccessCertification(email: String) {
        val key = redisUtil.getEmailCertificationSuccessKey(email)
        redisUtil.setData(key, true, EMAIL_CERTIFICATION_SUCCESS_EXPIRE_MINUTE, TimeUnit.MINUTES)
    }

    private fun getSecretCode(email: String): String {
        val key = redisUtil.getEmailCertificationKey(email)
        return redisUtil.getData(key)
            ?: throw BingsooException(AuthError.EMAIL_CERTIFICATION_NOT_FOUND)

    }

    private fun deleteSecretCode(email: String) {
        val key = redisUtil.getEmailCertificationKey(email)
        redisUtil.deleteData(key)
    }

    private fun isWrongCode(code: String, request: String): Boolean {
        return !Objects.equals(code, request)
    }

    private fun isNotCertified(email: String): Boolean {
        val key = redisUtil.getEmailCertificationSuccessKey(email)
        val isCertified: Boolean? = redisUtil.getData(key)
        return isCertified?.let { false } ?: true
    }
}