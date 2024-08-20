package bingsoochef.bingsoochef.toppping.persistence

import bingsoochef.bingsoochef.toppping.domain.ToppingType
import org.springframework.data.jpa.repository.JpaRepository

interface ToppingTypeRepository : JpaRepository<ToppingType, Long> {
}