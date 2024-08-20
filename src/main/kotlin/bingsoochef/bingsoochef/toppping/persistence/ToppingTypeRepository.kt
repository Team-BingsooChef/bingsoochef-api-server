package bingsoochef.bingsoochef.toppping.persistence

import bingsoochef.bingsoochef.toppping.domain.ToppingType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingTypeRepository : JpaRepository<ToppingType, Long> {
}