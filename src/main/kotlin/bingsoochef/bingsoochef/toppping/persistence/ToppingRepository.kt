package bingsoochef.bingsoochef.toppping.persistence

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.toppping.domain.Topping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : JpaRepository<Topping, Long> {

    fun countByBingsooIs(bingsoo: Bingsoo): Long
}