package bingsoochef.bingsoochef.bingsoo.domain

import jakarta.persistence.*

@Table(name = "bingsoo")
@Entity
class Bingsoo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bingsoo_id")
    var id: Long? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var taste: Taste
) {
    fun updateTaste(taste: Taste) {
        this.taste = taste
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Bingsoo) return false
        if (this === other) return true

        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}