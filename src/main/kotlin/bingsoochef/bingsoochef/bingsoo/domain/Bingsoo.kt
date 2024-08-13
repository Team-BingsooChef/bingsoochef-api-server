package bingsoochef.bingsoochef.bingsoo.domain

import jakarta.persistence.*

@Table(name = "bingsoo")
@Entity
class Bingsoo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bingsooId: Long? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var taste: Taste
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Bingsoo) return false
        if (this === other) return true

        if (bingsooId == null || other.bingsooId == null) return false
        return bingsooId == other.bingsooId
    }

    override fun hashCode(): Int {
        return bingsooId?.hashCode() ?: 0
    }
}