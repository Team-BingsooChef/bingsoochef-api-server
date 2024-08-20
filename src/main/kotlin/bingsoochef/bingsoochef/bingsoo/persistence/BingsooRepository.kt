package bingsoochef.bingsoochef.bingsoo.persistence

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BingsooRepository : JpaRepository<Bingsoo, Long> {
}