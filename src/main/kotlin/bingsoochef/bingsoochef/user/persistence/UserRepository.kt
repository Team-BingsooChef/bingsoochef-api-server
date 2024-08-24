package bingsoochef.bingsoochef.user.persistence

import bingsoochef.bingsoochef.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
}