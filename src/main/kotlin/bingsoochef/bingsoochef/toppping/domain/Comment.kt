package bingsoochef.bingsoochef.toppping.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "comment")
@Entity
class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    var id: Long? = null,

    @Column(name = "comment_content")
    var content: String,
    @Column(name = "comment_created_time")
    var createdTime: LocalDateTime
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