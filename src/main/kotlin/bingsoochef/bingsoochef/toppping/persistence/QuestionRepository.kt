package bingsoochef.bingsoochef.toppping.persistence

import bingsoochef.bingsoochef.toppping.domain.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
}