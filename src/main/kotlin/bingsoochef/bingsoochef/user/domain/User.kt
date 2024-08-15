package bingsoochef.bingsoochef.user.domain

import jakarta.persistence.*

@Table(name = "user")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null,
)