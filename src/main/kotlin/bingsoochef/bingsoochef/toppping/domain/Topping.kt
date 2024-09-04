package bingsoochef.bingsoochef.toppping.domain

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
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
    var isHiden: Boolean

) {
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