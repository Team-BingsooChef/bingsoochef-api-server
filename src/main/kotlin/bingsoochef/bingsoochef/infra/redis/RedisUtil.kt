package bingsoochef.bingsoochef.infra.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil(
    val redisTemplate: RedisTemplate<String, String>,
    val objectMapper: ObjectMapper
) {
    companion object {
        const val EMAIL_CERTIFICATION_PREFIX = "email_certification:"
        const val EMAIL_CERTIFICATION_SUCCESS_PREFIX = "email_certification_success:"
    }

    // 데이터 저장
    fun <T> setData(key: String, data: T, timeout: Long, timeUnit: TimeUnit): Boolean {
        return try {
            val value = objectMapper.writeValueAsString(data)
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit)
            true
        } catch (e: Exception) {
            false
        }
    }

    final inline fun <reified T> getData(key: String): T? {
        val value = redisTemplate.opsForValue().get(key)
        return try {
            objectMapper.readValue(value, T::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun deleteData(key: String): Boolean {
        return try {
            redisTemplate.delete(key)
        } catch (e: Exception) {
            false
        }
    }

    fun getEmailCertificationKey(email: String): String {
        return EMAIL_CERTIFICATION_PREFIX + email
    }

    fun getEmailCertificationSuccessKey(email: String): String {
        return EMAIL_CERTIFICATION_SUCCESS_PREFIX + email
    }
}