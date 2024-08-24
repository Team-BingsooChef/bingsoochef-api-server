package bingsoochef.bingsoochef.user.domain

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import jakarta.persistence.*

@Table(name = "user")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingsoo_id", unique = true)
    var bingsoo: Bingsoo
)