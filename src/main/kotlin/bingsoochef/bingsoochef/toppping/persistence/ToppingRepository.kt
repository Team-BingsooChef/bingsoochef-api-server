package bingsoochef.bingsoochef.toppping.persistence

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.toppping.domain.Topping
import bingsoochef.bingsoochef.user.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : JpaRepository<Topping, Long> {

    fun existsByBingsooAndChef(bingsoo: Bingsoo, chef: User): Boolean

    fun countByBingsooIs(bingsoo: Bingsoo): Long

    fun findAllByBingsoo(bingsoo: Bingsoo, pageable: Pageable): Page<Topping>
}