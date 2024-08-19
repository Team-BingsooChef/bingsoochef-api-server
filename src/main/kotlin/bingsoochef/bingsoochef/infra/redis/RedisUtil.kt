package bingsoochef.bingsoochef.infra.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun <T> setData(key: String, data: T, timeout: Long, timeUnit: TimeUnit): Boolean {
        try {
            val value = objectMapper.writeValueAsString(data)
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit)
        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun <T> getData(key: String, clazz: Class<T>): T? {
        val value = redisTemplate.opsForValue().get(key)

        try {
            return objectMapper.readValue(value, clazz)
        } catch (e: Exception) {
            return null
        }
    }

    fun deleteData(key: String): Boolean {
        return try {
            redisTemplate.delete(key)
        } catch (e: Exception) {
            false
        }
    }
}