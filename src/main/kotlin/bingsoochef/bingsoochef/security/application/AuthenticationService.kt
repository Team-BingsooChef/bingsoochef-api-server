package bingsoochef.bingsoochef.security.application

import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.AuthError
import bingsoochef.bingsoochef.infra.redis.RedisUtil
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.util.concurrent.TimeUnit

@Service
class AuthenticationService(
    private val redisUtil: RedisUtil,
    private val mailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine
) {
    companion object {
        private const val EMAIL_EXPIRE_TIME = 5L
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

    private fun createHtmlContent(certificationNumber: String): String {
        val context = Context()
        context.setVariable("certificationNumber", certificationNumber)
        return templateEngine.process("email-certification-template", context)
    }

    private fun saveSecretCode(email: String, secretCode: String) {
        val key = redisUtil.getEmailCertificationKey(email)
        redisUtil.setData(key, secretCode, EMAIL_EXPIRE_TIME, TimeUnit.MINUTES)
    }
}