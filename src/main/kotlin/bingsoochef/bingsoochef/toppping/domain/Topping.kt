package bingsoochef.bingsoochef.toppping.domain

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.ToppingError
import bingsoochef.bingsoochef.user.domain.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "topping")
@Entity
class Topping(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topping_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingsoo_id")
    var bingsoo: Bingsoo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef_id")
    var chef: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topping_type_id")
    var toppingType: ToppingType,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", unique = true)
    var comment: Comment?,

    @Column(nullable = false)
    var chefName: String,
    @Column(name = "topping_title", nullable = false)
    var title: String,
    @Column(name = "topping_content", nullable = false)
    var content: String,
    @Column(name = "topping_position")
    var position: Long,
    @Column(name = "topping_created_time")
    var createdTime: LocalDateTime,
    var isHidden: Boolean

) {
    fun isReadableBy(user: User) {
        // 셰프인 경우: 항상 접근 가능
        if (chef == user)
            return

        // 손님인 경우: 녹은 토핑에만 접근 가능
        if (user.bingsoo == bingsoo) {
            if (isHidden)
                throw BingsooException(ToppingError.TOPPING_UNFROZEN)
            return
        }

        // 셰프도 손님도 아닌 경우: 항상 접근 불가
        throw BingsooException(ToppingError.TOPPING_FORBIDDEN, "사용자 ${user.userId}은(는) 요청한 토핑에 접근할 수 없습니다.")
    }

    fun defrost() {
        isHidden = false
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Topping) return false
        if (this === other) return true

        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}