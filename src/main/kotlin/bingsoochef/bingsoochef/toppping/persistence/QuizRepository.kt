package bingsoochef.bingsoochef.toppping.persistence

import bingsoochef.bingsoochef.toppping.domain.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuizRepository : JpaRepository<Quiz, Long> {
    fun findByToppingId(toppingId: Long): Optional<Quiz>
}