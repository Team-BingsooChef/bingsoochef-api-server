package bingsoochef.bingsoochef.user.domain

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import jakarta.persistence.*

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null,

    @Column(name = "username", unique = true)
    var username: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "nickname")
    var nickname: String? = null,

    @Enumerated(EnumType.STRING)
    var userType: AccountType,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingsoo_id", unique = true)
    var bingsoo: Bingsoo? = null

) {
    constructor(username: String, password: String, userType: AccountType) : this(
        null,
        username,
        password,
        null,
        userType
    )
}